package edu.catolica.api.mikrotik.connection.controller;

import edu.catolica.api.mikrotik.connection.domain.dto.UserRegistration;
import edu.catolica.api.mikrotik.connection.domain.dto.LoginUser;
import edu.catolica.api.mikrotik.connection.domain.service.UserService;
import jakarta.validation.Valid;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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
    void deleteAccount(@Valid @PathVariable String username) throws MikrotikApiException {
        userService.deleteAccount(username);
    }

    @PutMapping("/update-account")
    void updateAccount(@Valid @RequestBody UserRegistration userRegistration) throws MikrotikApiException {
        userService.updateAccount(userRegistration);
    }
}
