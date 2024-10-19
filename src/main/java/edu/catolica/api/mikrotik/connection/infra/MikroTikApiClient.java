package edu.catolica.api.mikrotik.connection.infra;

import jakarta.annotation.PreDestroy;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.ApiConnectionException;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.SocketFactory;

@Component
public class MikroTikApiClient {
    private final ApiConnection connection;

    public MikroTikApiClient(
            @Value("${HOST}") String host,
            @Value("${PORT}") String port,
            @Value("${USER}") String mikrotikUser,
            @Value("${PASSWORD}") String mikrotikPassword
    ) throws MikrotikApiException {
        this.connection = ApiConnection.connect(
                SocketFactory.getDefault(),
                host,
                Integer.parseInt(port),
                5000
        );

        connection.login(mikrotikUser, mikrotikPassword);
    }

    @PreDestroy
    private void disconnect() throws ApiConnectionException {
        if (connection != null && connection.isConnected())
            this.connection.close();
    }

    public ApiConnection getConnection() {
        return connection;
    }
}
