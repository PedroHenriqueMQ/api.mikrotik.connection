package edu.catolica.api.mikrotik.connection.controller;

import edu.catolica.api.mikrotik.connection.dto.UsuarioCadastro;
import edu.catolica.api.mikrotik.connection.dto.UsuarioLogin;
import edu.catolica.api.mikrotik.connection.service.UsuarioService;
import jakarta.validation.Valid;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    void login(@Valid @RequestBody UsuarioLogin usuarioLogin) {

    }

    @PostMapping("/logout")
    void logout() {

    }

    @PostMapping("/create-account")
    void createAccount(@Valid @RequestBody UsuarioCadastro usuarioCadastro) throws MikrotikApiException {
        usuarioService.criarConta(usuarioCadastro);
    }

    @DeleteMapping("/delete-account/{usuario}")
    void deleteAccount(@Valid @PathVariable String usuario) throws MikrotikApiException {
        usuarioService.apagarConta(usuario);
    }

    @PutMapping("/update-account")
    void updateAccount(@Valid @RequestBody UsuarioCadastro usuario) throws MikrotikApiException {
        usuarioService.atualizarConta(usuario);
    }
}
