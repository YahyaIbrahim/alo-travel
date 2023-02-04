package com.wellware.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {


    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
}