package edu.catolica.api.mikrotik.connection.domain.dto;

public record OperationResult(
    boolean success,
    String message
) {}