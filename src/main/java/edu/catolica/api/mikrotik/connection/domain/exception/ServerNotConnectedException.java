package edu.catolica.api.mikrotik.connection.domain.exception;

import me.legrange.mikrotik.ApiConnectionException;

public class ServerNotConnectedException extends ApiConnectionException {
    public ServerNotConnectedException(String message) {
        super(message);
    }
}
