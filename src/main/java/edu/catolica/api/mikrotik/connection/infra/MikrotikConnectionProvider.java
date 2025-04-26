package edu.catolica.api.mikrotik.connection.infra;

import edu.catolica.api.mikrotik.connection.config.MikrotikProperties;
import edu.catolica.api.mikrotik.connection.domain.exception.ServerConnectionTimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.stereotype.Component;

import javax.net.SocketFactory;

@Slf4j
@Component
@RequiredArgsConstructor
public class MikrotikConnectionProvider {
    private final MikrotikProperties props;
    private ApiConnection connection;

    public synchronized ApiConnection getConnection() throws ServerConnectionTimeoutException {
        try {
            if (this.connection == null || !this.connection.isConnected()) {
                log.info("Conectando ao Mikrotik {}:{}", props.getHost(), props.getPort());
                this.connection = ApiConnection.connect(
                        SocketFactory.getDefault(),
                        props.getHost(),
                        props.getPort(),
                        5000
                );

                this.connection.login(props.getUser(), props.getPassword());
            }
            return connection;
        } catch (MikrotikApiException e){
            log.error("Falha ao conectar ao Mikrotik: {}", e.getMessage());
            throw new ServerConnectionTimeoutException();
        }
    }
}
