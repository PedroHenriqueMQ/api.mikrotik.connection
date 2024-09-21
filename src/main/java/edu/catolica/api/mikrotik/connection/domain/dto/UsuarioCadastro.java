package edu.catolica.api.mikrotik.connection.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCadastro(
        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("registration")
        String matricula,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @Email(message = "Este campo só aceita emails com formato válido.")
        String email,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("name")
        String nome,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("phone_number")
        String telefone,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("user")
        String usuario,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("password")
        String senha
) {
}
