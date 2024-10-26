package edu.catolica.api.mikrotik.connection.service;

import edu.catolica.api.mikrotik.connection.dto.UsuarioCadastro;
import edu.catolica.api.mikrotik.connection.infra.MikroTikApiClient;
import lombok.RequiredArgsConstructor;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final MikroTikApiClient mikroTikApiClient ;

    public void criarConta(UsuarioCadastro usuario) throws MikrotikApiException {
        String comando = String.format(
                "/ip/hotspot/user/add name=%s password=\"%s\" comment=\"%s\" profile=default limit-uptime=1h",
                usuario.usuario(),
                usuario.senha(),
                formularComentario(usuario)
        );

        mikroTikApiClient.getConnection().execute(comando);
    }

    public void apagarConta(String usuario) throws MikrotikApiException {
        var usuarioBusca = encontrarUsuario(usuario);

        if (!usuarioBusca.isEmpty()) {
            String userId = usuarioBusca.get(0).get(".id");

            String comandoDeletar = String.format("/ip/hotspot/user/remove .id=%s", userId);
            mikroTikApiClient.getConnection().execute(comandoDeletar);
        }

        System.out.println("Usuário não encontrado.");
    }


    public void atualizarConta(UsuarioCadastro usuario) throws MikrotikApiException {
        var usuarioBusca = encontrarUsuario(usuario.usuario());

        if (!usuarioBusca.isEmpty()) {
            String userId = usuarioBusca.get(0).get(".id");

            String comandoAtualizar = String.format("/ip/hotspot/user/set .id=%s name=%s password=\"%s\" comment=\"%s\"",
                    userId,
                    usuario.usuario(),
                    usuario.senha(),
                    formularComentario(usuario)
            );

            mikroTikApiClient.getConnection().execute(comandoAtualizar);
        }

        System.out.println("Usuário não encontrado.");
    }

    private List<Map<String, String>> encontrarUsuario(String usuario) throws MikrotikApiException {
        String comandoPesquisar = String.format("/ip/hotspot/user/print where name=%s", usuario);
        List<Map<String, String>> resultadoPesquisa = mikroTikApiClient.getConnection().execute(comandoPesquisar);

        return resultadoPesquisa;
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
