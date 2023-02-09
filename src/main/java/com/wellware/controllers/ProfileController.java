package com.wellware.controllers;

import com.wellware.DTO.ProfileDTO;
import com.wellware.data.entities.Country;
import com.wellware.data.entities.Profile;
import com.wellware.data.repositories.CountryRepository;
import com.wellware.services.framework.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/profile")
public class ProfileController {


    @Autowired
    private ProfileService profileService;


    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private AuthenticationManager authManager;


    @PreAuthorize("hasAnyRole({'SUPERADMIN','ADMIN','USER'})")
    @RequestMapping("")
    public String profile(Model model, HttpServletResponse response, HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) auth.getPrincipal();
        Profile profile = profileService.getProfileByEmail(userDetails.getUsername());

        model.addAttribute("profile", profile);
        model.addAttribute("displayName", profile.getDisplayName());
        model.addAttribute("countries", countryRepository.findAll());
        return "profile";
    }

    @PreAuthorize("hasAnyRole({'SUPERADMIN','ADMIN','USER'})")
    @PostMapping("/updateProfile")
    public String updateProfile(Model model,
                                @ModelAttribute("profile")Profile profileDTO,
                                BindingResult result,
                                WebRequest request,
                                Errors errors){

//        if (result.hasErrors()) {
//            model.addAttribute("errorCode",403);
//            model.addAttribute("errorMsg","ops, Something wrong is happend");
//            return "error";
//        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) auth.getPrincipal();
        Profile updateProfile = profileService.getProfileByEmail(userDetails.getUsername());

        updateProfile.setEmail(profileDTO.getEmail());
        updateProfile.setPhone(profileDTO.getPhone());
        updateProfile.setDisplayName(profileDTO.getDisplayName());
        updateProfile.setCountry(profileDTO.getCountry());

        profileService.saveRegisteredUser(updateProfile);


        model.addAttribute("profile", updateProfile);
        model.addAttribute("displayName", updateProfile.getDisplayName());
        model.addAttribute("countries", countryRepository.findAll());
        return "redirect:/profile";
    }

}
