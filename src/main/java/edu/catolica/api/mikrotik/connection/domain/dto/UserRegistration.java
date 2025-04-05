package edu.catolica.api.mikrotik.connection.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.*;

public record UserRegistration(
    @NotBlank(message = "Este campo não pode estar vazio.")
    @Pattern(regexp = "\\d+", message = "Este campo só aceita valores numéricos.")
    @Size(min = 8, max = 8, message = "Este campo deve ter exatamente 8 dígitos.")
    @JsonAlias("registration")
    String registration,

    @NotBlank(message = "Este campo não pode estar vazio.")
    @Email(message = "Este campo só aceita emails com formato válido.")
    String email,

    @NotBlank(message = "Este campo não pode estar vazio.")
    @JsonAlias("name")
    String name,

    @NotBlank(message = "Este campo não pode estar vazio.")
    @Pattern(regexp = "\\d+", message = "Este campo só aceita valores numéricos.")
    @Size(min = 11, max = 11, message = "Este campo deve ter exatamente 11 dígitos.")
    @JsonAlias("phone_number")
    String phoneNumber,

    @NotBlank(message = "Este campo não pode estar vazio.")
    @JsonAlias("user")
    String user,

    @NotBlank(message = "Este campo não pode estar vazio.")
    @JsonAlias("password")
     String password
) {
    public UserRegistration(String registration, String email, String name, String phoneNumber, String user, String password) {
        this.registration = registration;
        this.email = email.toLowerCase();
        this.name = name.toLowerCase();
        this.phoneNumber = phoneNumber;
        this.user = user.toLowerCase();
        this.password = password.toLowerCase();
    }
}
