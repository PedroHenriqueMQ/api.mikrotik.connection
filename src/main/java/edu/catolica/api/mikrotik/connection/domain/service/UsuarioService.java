package edu.catolica.api.mikrotik.connection.domain.service;

import edu.catolica.api.mikrotik.connection.domain.dto.UsuarioLogin;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.ApiConnectionException;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.stereotype.Service;

import javax.net.SocketFactory;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    public ApiConnection efetuarLogin(UsuarioLogin usuarioLogin) {
        try {
            ApiConnection connection = ApiConnection.connect(
                    SocketFactory.getDefault(),
                    "banana",
                    banana,
                    banana
            );

            connection.login(usuarioLogin.usuario(), usuarioLogin.senha());

            List<Map<String, String>> results = connection.execute("/user/active/print");

            results.forEach(
                    (result) -> {
                        for (Map.Entry<String, String> entry : result.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                    }
            );

            //for(var result : results) {
            //    for (Map.Entry<String, String> entry : result.entrySet()) {
            //        System.out.println(entry.getKey() + ": " + entry.getValue());
            //    }
            //}

            return connection;
        } catch (MikrotikApiException mikrotikApiException) {
            return null;
        }
    }

    public boolean fazerLogout(ApiConnection connection) {
        try {
            connection.close();
            return true;
        } catch (ApiConnectionException apiConnectionException) {
            return false;
        }
    }
}
