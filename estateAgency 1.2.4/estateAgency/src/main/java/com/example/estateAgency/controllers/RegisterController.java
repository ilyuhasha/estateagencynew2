package com.example.estateAgency.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @GetMapping("/register")
    public String showRegistrationForm() {
        logger.info("The user went to the register tab");
        return "register.html";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        logger.info("The user went to the login tab");
        return "login.html";
    }

    @GetMapping("/home")
    public String Home() {
        logger.info("The user went to the home tab");
        return "home.html";
    }
}

