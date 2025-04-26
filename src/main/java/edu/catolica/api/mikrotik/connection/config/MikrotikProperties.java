package edu.catolica.api.mikrotik.connection.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "mikrotik")
@Data
public class MikrotikProperties {
    private String host;
    private int port;
    private String user;
    private String password;
}