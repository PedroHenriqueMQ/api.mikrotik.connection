package edu.catolica.api.mikrotik.connection.infra.exception;

import edu.catolica.api.mikrotik.connection.domain.dto.OperationResult;
import edu.catolica.api.mikrotik.connection.domain.exception.ServerConnectionTimeoutException;
import edu.catolica.api.mikrotik.connection.domain.exception.UserNotFoundException;
import me.legrange.mikrotik.MikrotikApiException;
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
                ex.getMessage()
        );

        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerConnectionTimeoutException.class)
    public ResponseEntity<OperationResult> handleServerConnectionTimeoutException(ServerConnectionTimeoutException ex) {
        var result = new OperationResult(
                false,
                ex.getMessage()
        );

        return new ResponseEntity<>(result, HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(MikrotikApiException.class)
    public ResponseEntity<OperationResult> handleMikrotikApiException(MikrotikApiException ex) {
        var message = "Serviço inalcançável no momento: " + ex.getMessage();

        var result = new OperationResult(
                false,
                message
        );

        return new ResponseEntity<>(result, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
