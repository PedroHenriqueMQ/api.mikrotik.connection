package edu.catolica.api.mikrotik.connection.domain.service;

import edu.catolica.api.mikrotik.connection.domain.dto.UsuarioCadastro;
import edu.catolica.api.mikrotik.connection.domain.dto.UsuarioLogin;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.ApiConnectionException;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.SocketFactory;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    ApiConnection connection;

    public UsuarioService(
            @Value("${HOST}") String host,
            @Value("${PORT}") String port,
            @Value("${USER}") String mikrotikUser,
            @Value("${PASSWORD}") String mikrotikPassword
    ) throws MikrotikApiException {
        connection = ApiConnection.connect(
                SocketFactory.getDefault(),
                host,
                Integer.parseInt(port),
                5000
        );

        connection.login(mikrotikUser, mikrotikPassword);
    }

    public boolean criarConta(UsuarioCadastro usuarioCadastro) throws MikrotikApiException {
        String comentario = String.format(
                "MATRICULA:%s; EMAIL:%s; NOME:%s; TELEFONE:%s;",
                usuarioCadastro.matricula(),
                usuarioCadastro.email(),
                usuarioCadastro.nome(),
                usuarioCadastro.telefone()
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
        var usuarioBusca = encontrarUsuario(usuario);

        if (!usuarioBusca.isEmpty()) {
            String userId = usuarioBusca.get(0).get(".id");

            String comandoDeletar = String.format("/ip/hotspot/user/remove .id=%s", userId);

            connection.execute(comandoDeletar);

            System.out.println("Usuário removido com sucesso.");
            return true;
        }

        System.out.println("Usuário não encontrado.");
        return false;
    }


    public boolean atualizarConta(UsuarioCadastro usuario) throws MikrotikApiException {
        var usuarioBusca = encontrarUsuario(usuario.usuario());

        if (!usuarioBusca.isEmpty()) {
            String userId = usuarioBusca.get(0).get(".id");

            String comentario = String.format(
                    "MATRICULA:%s; EMAIL:%s; NOME:%s; TELEFONE:%s;",
                    usuario.matricula(),
                    usuario.email(),
                    usuario.nome(),
                    usuario.telefone()
            );

            String comandoAtualizar = String.format("/ip/hotspot/user/set .id=%s name=%s password=\"%s\" comment=\"%s\"",
                    userId,
                    usuario.usuario(),
                    usuario.senha(),
                    comentario
            );

            connection.execute(comandoAtualizar);

            System.out.println("Usuário atualizado com sucesso.");
            return true;
        }

        System.out.println("Usuário não encontrado.");
        return false;
    }

    private List<Map<String, String>> encontrarUsuario(String usuario) throws MikrotikApiException {
        String comandoPesquisar = String.format("/ip/hotspot/user/print where name=%s", usuario);

        List<Map<String, String>> resultadoPesquisa = connection.execute(comandoPesquisar);

        return resultadoPesquisa;
    }

}
