package edu.catolica.api.mikrotik.connection.controller;

import edu.catolica.api.mikrotik.connection.domain.dto.UsuarioCadastro;
import edu.catolica.api.mikrotik.connection.domain.dto.UsuarioLogin;
import edu.catolica.api.mikrotik.connection.domain.service.UsuarioService;
import jakarta.validation.Valid;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    void login(@Valid @RequestBody UsuarioLogin usuarioSystemLogin) {

    }

    @PostMapping("/logout")
    void logout() {

    }

    @PostMapping("/create-account")
    void createAccount(@Valid @RequestBody UsuarioCadastro usuarioCadastro) throws MikrotikApiException {
        System.out.println(usuarioService.criarConta(usuarioCadastro));
    }

    @DeleteMapping("/delete-account/{usuario}")
    void deleteAccount(@Valid @PathVariable String usuario) throws MikrotikApiException {
        System.out.println(usuarioService.apagarConta(usuario));
    }

    @PutMapping("/update-account")
    void updateAccount(@Valid @RequestBody UsuarioCadastro usuario) throws MikrotikApiException {
        System.out.println(usuarioService.atualizarConta(usuario));
    }
}
