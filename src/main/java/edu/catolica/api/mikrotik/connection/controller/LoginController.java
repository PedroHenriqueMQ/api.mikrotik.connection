package edu.catolica.api.mikrotik.connection.controller;

import edu.catolica.api.mikrotik.connection.domain.dto.UsuarioLogin;
import edu.catolica.api.mikrotik.connection.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    void login(@Valid @RequestBody UsuarioLogin usuarioLogin) {
        System.out.println(usuarioService.efetuarLogin(usuarioLogin));
    }

    @PostMapping("/logout")
    void logout() {
        System.out.println();
    }
}
