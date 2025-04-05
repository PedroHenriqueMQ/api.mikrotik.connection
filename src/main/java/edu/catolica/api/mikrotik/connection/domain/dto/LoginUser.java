package edu.catolica.api.mikrotik.connection.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record LoginUser(
    @NotBlank(message = "Este campo não pode estar vazio.")
    @JsonAlias("user")
    String username,

    @NotBlank(message = "Este campo não pode estar vazio.")
    @JsonAlias("password")
    String password
) { }
