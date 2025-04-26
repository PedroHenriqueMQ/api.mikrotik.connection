package edu.catolica.api.mikrotik.connection.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("Usuário não encontrado: " + username);
    }
}
