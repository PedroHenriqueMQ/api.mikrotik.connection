package edu.catolica.api.mikrotik.connection.domain.service;

import edu.catolica.api.mikrotik.connection.domain.dto.UsuarioCadastro;
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
    ApiConnection connection;


    public boolean efetuarLogin(UsuarioLogin usuarioLogin) throws MikrotikApiException {
        connection = ApiConnection.connect(
                SocketFactory.getDefault(),
                System.getenv("HOST"),
                Integer.parseInt(System.getenv("PORT")),
                2000
        );

        connection.login(usuarioLogin.usuario(), usuarioLogin.senha());

        return true;
    }


    public boolean fazerLogout(ApiConnection connection) throws ApiConnectionException {
        connection.close();
        return true;
    }

    public boolean criarConta(UsuarioCadastro usuarioCadastro) throws MikrotikApiException {
        String comentario = String.format(
                "MATRICULA:%s; EMAIL:%s; NOME:%s; TELEFONE:%s;",
                usuarioCadastro.matricula().toLowerCase(),
                usuarioCadastro.email().toLowerCase(),
                usuarioCadastro.nome().toLowerCase(),
                usuarioCadastro.telefone().toLowerCase()
        );

        String comando = String.format(
                "/ip/hotspot/user/add name=%s password=\"%s\" comment=\"%s\" profile=default limit-uptime=1h",
                usuarioCadastro.usuario(),
                usuarioCadastro.senha(),
                comentario
        );

        connection.execute(comando);

        return true;
    }

    public boolean apagarConta(String usuario) throws MikrotikApiException {
        String comandoPesquisar = String.format("/ip/hotspot/user/print where name=\"%s\"", usuario);

        List<Map<String, String>> result = connection.execute(comandoPesquisar);

        if (!result.isEmpty()) {
            String userId = result.get(0).get(".id");

            String comandoDeletar = String.format("/ip/hotspot/user/remove .id=%s", userId);

            connection.execute(comandoDeletar);

            System.out.println("Usuário removido com sucesso.");
            return true;
        }

        System.out.println("Usuário não encontrado.");
        return false;
    }

}
