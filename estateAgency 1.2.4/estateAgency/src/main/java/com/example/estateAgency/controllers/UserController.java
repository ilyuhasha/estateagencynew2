package com.example.estateAgency.controllers;


import com.example.estateAgency.models.User;
import com.example.estateAgency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Object registerUser(@RequestParam String login, @RequestParam String password) {
        User existingUser = userRepository.findByLogin(login);
        try {
            User user = new User();
            user.setLogin(login);
            String encryptedPassword = new BCryptPasswordEncoder().encode(password);
            user.setPassword(encryptedPassword);
//            user.setPoints(0);
            User savedUser = userRepository.save(user);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while registering user: " + e.getMessage());
        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestParam String login, @RequestParam String password) {
//        User existingUser = userRepository.findByLogin(login);
//        if (existingUser != null) {
//            return ResponseEntity.badRequest().body("User with such login already exists");
//        }
//
//        try {
//            User user = new User();
//            user.setLogin(login);
//            String encryptedPassword = new BCryptPasswordEncoder().encode(password);
//            user.setPassword(encryptedPassword);
//
//            if (user.getLogin().equals("IlYA")) {
//                user.setRole("ROLE_ILYA");
//            } else {
//                user.setRole("ROLE_USER");
//            }
//
//            User savedUser = userRepository.save(user);
//
//            return ResponseEntity.ok("User " + savedUser.getLogin() + " successfully registered");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error while registering user: " + e.getMessage());
//
//        }
//    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return "redirect:/";
    }
}


