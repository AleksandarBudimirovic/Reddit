package com.example.reddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "login"; // This will serve the login.html template as the initial page
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Serve login.html template
    }
}
