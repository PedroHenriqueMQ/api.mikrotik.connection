package edu.catolica.api.mikrotik.connection.domain.exception;

import me.legrange.mikrotik.MikrotikApiException;

public class ServerConnectionTimeoutException extends MikrotikApiException {
    public ServerConnectionTimeoutException() {
        super("Falha de conexão com o servidor");
    }
}
