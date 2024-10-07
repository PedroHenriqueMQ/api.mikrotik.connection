package edu.catolica.api.mikrotik.connection.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.*;

public record UsuarioCadastro(
        @NotBlank(message = "Este campo não pode estar vazio.")
        @Pattern(regexp = "\\d+", message = "Este campo só aceita valores numéricos.")
        @Size(min = 8, max = 8, message = "Este campo deve ter exatamente 8 dígitos.")
        @JsonAlias("registration")
        String matricula,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @Email(message = "Este campo só aceita emails com formato válido.")
        String email,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("name")
        String nome,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @Pattern(regexp = "\\d+", message = "Este campo só aceita valores numéricos.")
        @Size(min = 11, max = 11, message = "Este campo deve ter exatamente 11 dígitos.")
        @JsonAlias("phone_number")
        String telefone,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("user")
        String usuario,

        @NotBlank(message = "Este campo não pode estar vazio.")
        @JsonAlias("password")
        String senha
) {
        public UsuarioCadastro(String matricula, String email, String nome, String telefone, String usuario, String senha) {
                this.matricula = matricula;
                this.email = email.toLowerCase();
                this.nome = nome.toLowerCase();
                this.telefone = telefone;
                this.usuario = usuario.toLowerCase();
                this.senha = senha.toLowerCase();
        }
}
