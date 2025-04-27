package edu.catolica.api.mikrotik.connection.infra;

import edu.catolica.api.mikrotik.connection.config.MikrotikProperties;
import edu.catolica.api.mikrotik.connection.domain.exception.ServerConnectionTimeoutException;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    private volatile ApiConnection connection;

    @CircuitBreaker(name = "mikrotik", fallbackMethod = "onCircuitOpen")
    @Retry(name = "mikrotik", fallbackMethod = "onRetryExhausted")
    public ApiConnection getConnection() throws ServerConnectionTimeoutException {
        var isConnectionValid = connection != null && connection.isConnected();

        if (!isConnectionValid)
            synchronized (this) {
                if (!isConnectionValid)
                    establishConnection();
            }

        return connection;
    }

    private void establishConnection() throws ServerConnectionTimeoutException {
        try {
            log.info("Conectando ao Mikrotik {}:{}", props.getHost(), props.getPort());
            connection = ApiConnection.connect(
                    SocketFactory.getDefault(),
                    props.getHost(),
                    props.getPort(),
                    500
            );
            connection.login(props.getUser(), props.getPassword());
        } catch (MikrotikApiException e) {
            log.error("Falha ao conectar ao Mikrotik: {}", e.getMessage());
            throw new ServerConnectionTimeoutException("Não foi possível conectar ao Mikrotik", e);
        }
    }

    private ApiConnection onRetryExhausted(Throwable t) throws ServerConnectionTimeoutException {
        log.warn("Retries esgotados: {}", t.getMessage());
        throw new ServerConnectionTimeoutException("Tentativas de conexão esgotadas", t);
    }

    private ApiConnection onCircuitOpen(Throwable t) throws ServerConnectionTimeoutException {
        log.warn("Circuit breaker aberto: {}", t.getMessage());
        throw new ServerConnectionTimeoutException("Circuit breaker aberto: Mikrotik indisponível", t);
    }
}
