package com.wellware.controllers;

import com.wellware.DTO.OnRegistrationCompleteEvent;
import com.wellware.DTO.ProfileDTO;
import com.wellware.data.entities.Profile;
import com.wellware.data.entities.VerificationToken;
import com.wellware.data.repositories.CountryRepository;
import com.wellware.exceptions.EmailExistsException;
import com.wellware.services.framework.ProfileService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
@Slf4j
public class AuthenticationController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping(value = "/signup")
    public ModelAndView registerUserAccount(
            @ModelAttribute("profile") @Valid ProfileDTO profileDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        if (result.hasErrors()) {
            return new ModelAndView("register", "profile", profileDto);
        }

        Profile registered = createUserAccount(profileDto);

        // registration failed
        if (registered == null) {
            result.rejectValue("email", profileDto.getEmail() + " already exists, please use anther email address");
        }

        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            me.printStackTrace();
            return new ModelAndView("registration-failed", "profile", profileDto);
        }

        return new ModelAndView("redirect:/", "profile", profileDto);
    }


    @GetMapping(value = "/registrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        VerificationToken verificationToken = profileService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "Invalid token";
            model.addAttribute("message", message);
            return "redirect:/error.html";
        }

        Profile user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = "Verfication link expired!";
            model.addAttribute("errorCode", 403);
            model.addAttribute("errorMsg", messageValue);
            return "redirect:/error.html";
        }

        user.setEnabled(true);
        profileService.saveRegisteredUser(user);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String loadRegistrationForm(Model model) {
        model.addAttribute("profile", new ProfileDTO());
        model.addAttribute("countries", countryRepository.findAll());
        return "register";
    }


    private Profile createUserAccount(ProfileDTO profile) {
        Profile registered = null;
        try {
            registered = profileService.registerNewAccount(profile);
        } catch (EmailExistsException e) {
            e.printStackTrace();
            return null;
        }
        return registered;
    }

}
