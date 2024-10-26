package edu.catolica.api.mikrotik.connection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RouteController {
    @PostMapping("/")
    public String redirection() {
        return "login";
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/singup")
    public String singup() {
        return "singup";
    }
}