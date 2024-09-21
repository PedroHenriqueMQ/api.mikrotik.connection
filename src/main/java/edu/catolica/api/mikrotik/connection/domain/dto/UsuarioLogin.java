package edu.catolica.api.mikrotik.connection.domain.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLogin(
        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("user")
        String usuario,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("password")
        String senha
) { }
