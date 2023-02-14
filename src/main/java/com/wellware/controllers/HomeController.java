package com.wellware.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellware.data.entities.Profile;
import com.wellware.data.repositories.CountryRepository;
import com.wellware.data.repositories.ProfileRepository;
import com.wellware.services.framework.AutoCompleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
            User userDetails = (User) auth.getPrincipal();
            Profile profile = profileRepository.findByEmail(userDetails.getUsername());
            log.info(userDetails.toString());
            model.addAttribute("displayName", profile.getDisplayName());
        }
        return "index";
    }




    @GetMapping("fragment-expression")
    public String fragmentExpression(){
        return "fragment-expression";
    }
}
