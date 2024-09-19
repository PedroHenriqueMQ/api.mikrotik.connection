package edu.catolica.api.mikrotik.connection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/singup")
    public String singup() {
        return "singup";
    }
}