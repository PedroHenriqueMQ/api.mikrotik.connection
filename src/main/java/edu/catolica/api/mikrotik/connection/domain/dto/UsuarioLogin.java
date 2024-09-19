package edu.catolica.api.mikrotik.connection.domain.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioLogin(

        @NotBlank(message = "Este campo não pode estar vazio.")
        @NotNull(message = "Este campo não pode ser nulo.")
        @JsonAlias("user")
        String usuario,
        @NotBlank(message = "Este campo não pode estar vazio.")
        @NotNull(message = "Este campo não pode ser nulo.")
        @JsonAlias("password")
        String senha
) { }
