package edu.catolica.api.mikrotik.connection.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerMessager {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        StringBuilder erroMsg = new StringBuilder();

        for (FieldError erro : exception.getBindingResult().getFieldErrors()) {
            erroMsg.append(erro.getField().toUpperCase() + ": " + erro.getDefaultMessage() + "\n");
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroMsg.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentExceptions(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
