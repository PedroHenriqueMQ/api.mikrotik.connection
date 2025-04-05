package edu.catolica.api.mikrotik.connection.controller;

import edu.catolica.api.mikrotik.connection.domain.dto.UserRegistration;
import edu.catolica.api.mikrotik.connection.domain.dto.LoginUser;
import edu.catolica.api.mikrotik.connection.domain.service.UserService;
import jakarta.validation.Valid;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    void login(@Valid @RequestBody LoginUser loginUser) {

    }

    @PostMapping("/logout")
    void logout() {

    }

    @PostMapping("/create-account")
    void createAccount(@Valid @RequestBody UserRegistration userRegistration) throws MikrotikApiException {
        userService.createAccount(userRegistration);
    }

    @DeleteMapping("/delete-account/{username}")
    void deleteAccount(@Valid @PathVariable String usuario) throws MikrotikApiException {
        userService.deleteAccount(usuario);
    }

    @PutMapping("/update-account")
    void updateAccount(@Valid @RequestBody UserRegistration usuario) throws MikrotikApiException {
        userService.updateAccount(usuario);
    }
}
