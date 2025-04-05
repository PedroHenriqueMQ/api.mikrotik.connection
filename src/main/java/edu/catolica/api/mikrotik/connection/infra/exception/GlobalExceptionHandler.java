package edu.catolica.api.mikrotik.connection.infra.exception;

import edu.catolica.api.mikrotik.connection.domain.dto.OperationResult;
import edu.catolica.api.mikrotik.connection.domain.exception.ServerNotConnectedException;
import edu.catolica.api.mikrotik.connection.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<OperationResult> handleUserNotFoundException(UserNotFoundException ex) {
        var result = new OperationResult(
                false,
                "Usuário não encontrado: " + ex.getMessage()
        );

        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerNotConnectedException.class)
    public ResponseEntity<OperationResult> handleUnsatisfiedDependencyException(ServerNotConnectedException ex) {
        var result = new OperationResult(
                false,
                "Falha de conexão do servidor: " + ex.getMessage()
        );

        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
