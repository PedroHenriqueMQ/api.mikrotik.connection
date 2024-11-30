package edu.catolica.api.mikrotik.connection.service;

import edu.catolica.api.mikrotik.connection.dto.UsuarioCadastro;
import edu.catolica.api.mikrotik.connection.infra.MikroTikApiClient;
import lombok.RequiredArgsConstructor;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final MikroTikApiClient mikroTikApiClient;

    public void criarConta(UsuarioCadastro usuario) throws MikrotikApiException {
        var verificarUsuarioExistente = encontrarUsuario(usuario.usuario());

        if(verificarUsuarioExistente != null)
            throw new IllegalArgumentException("Esse usuário já existe.");

        var comando = String.format(
                "/ip/hotspot/user/add name=%s password=\"%s\" comment=\"%s\" profile=default limit-uptime=1h",
                usuario.usuario(),
                usuario.senha(),
                formularComentario(usuario)
        );

        mikroTikApiClient.getConnection().execute(comando);
    }

    public void apagarConta(String usuario) throws MikrotikApiException {
        var usuarioBusca = encontrarUsuario(usuario);
        var userId = usuarioBusca.get(".id");

        var comandoDeletar = String.format("/ip/hotspot/user/remove .id=%s", userId);
        mikroTikApiClient.getConnection().execute(comandoDeletar);
    }

    public void atualizarConta(UsuarioCadastro usuario) throws MikrotikApiException {
        var usuarioBusca = encontrarUsuario(usuario.usuario());
        var userId = usuarioBusca.get(".id");

        var comandoAtualizar = String.format("/ip/hotspot/user/set .id=%s name=%s password=\"%s\" comment=\"%s\"",
                userId,
                usuario.usuario(),
                usuario.senha(),
                formularComentario(usuario)
        );

        mikroTikApiClient.getConnection().execute(comandoAtualizar);
    }

    private Map<String, String> encontrarUsuario(String usuario) throws MikrotikApiException {
        var comandoPesquisar = String.format("/ip/hotspot/user/print where name=%s", usuario);
        var resultadoPesquisa = mikroTikApiClient.getConnection().execute(comandoPesquisar);
        
        return resultadoPesquisa.isEmpty() ? null : resultadoPesquisa.getFirst();
    }

    private String formularComentario(UsuarioCadastro usuario) {
        return String.format(
                "MATRICULA:%s; EMAIL:%s; NOME:%s; TELEFONE:%s;",
                usuario.matricula(),
                usuario.email(),
                usuario.nome(),
                usuario.telefone()
        );
    }
}
