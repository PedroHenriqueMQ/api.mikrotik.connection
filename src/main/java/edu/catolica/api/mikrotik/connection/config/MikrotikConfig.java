package edu.catolica.api.mikrotik.connection.config;

import lombok.extern.slf4j.Slf4j;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.ApiConnectionException;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.SocketFactory;

@Slf4j
@Configuration
public class MikrotikConfig {

    @Bean
    public ApiConnection mikrotikConnection(
            @Value("${HOST}") String host,
            @Value("${PORT}") String port,
            @Value("${USER}") String mikrotikUser,
            @Value("${PASSWORD}") String mikrotikPassword)
    throws MikrotikApiException {
        try {
            ApiConnection connection = ApiConnection.connect(
                    SocketFactory.getDefault(),
                    host,
                    Integer.parseInt(port),
                    5000
            );

            connection.login(mikrotikUser, mikrotikPassword);
            return connection;
        } catch (ApiConnectionException e) {
            var errorMessage = "Erro ao conectar ao Mikrotik: " + e.getMessage();
            log.error(errorMessage);
            return null;
        }
    }
}
