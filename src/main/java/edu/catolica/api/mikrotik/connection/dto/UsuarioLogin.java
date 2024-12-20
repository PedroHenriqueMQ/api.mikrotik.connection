package edu.catolica.api.mikrotik.connection.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLogin(
        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("username")
        String usuario,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("password")
        String senha
) { }
