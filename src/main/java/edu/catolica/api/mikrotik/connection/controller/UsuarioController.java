package edu.catolica.api.mikrotik.connection.controller;

import edu.catolica.api.mikrotik.connection.dto.UsuarioCadastro;
import edu.catolica.api.mikrotik.connection.service.UsuarioService;
import jakarta.validation.Valid;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/create-account")
    ResponseEntity<Void> createAccount(@Valid @RequestBody UsuarioCadastro usuarioCadastro) throws MikrotikApiException {
        usuarioService.criarConta(usuarioCadastro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete-account/{usuario}")
    ResponseEntity<Void> deleteAccount(@PathVariable String usuario) throws MikrotikApiException {
        usuarioService.apagarConta(usuario);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update-account")
    ResponseEntity<Void> updateAccount(@Valid @RequestBody UsuarioCadastro usuario) throws MikrotikApiException {
        usuarioService.atualizarConta(usuario);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
