package com.wellware.controllers;

import com.wellware.data.entities.Profile;
import com.wellware.data.repositories.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private ProfileRepository profileRepository;

    @RequestMapping("/")
    public String index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated())
            return "redirect:/home";
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.isAuthenticated())
            return "redirect:/";
        if(auth.getPrincipal() != "anonymousUser") {
            Profile userDetails = (Profile) auth.getPrincipal();
            Profile profile = profileRepository.findByEmail(userDetails.getEmail());
            log.info(profile.toString());
            model.addAttribute("displayName", profile.getDisplayName());
        }
        return "index";
    }

    @GetMapping("fragment-expression")
    public String fragmentExpression(){
        return "fragment-expression";
    }
}
