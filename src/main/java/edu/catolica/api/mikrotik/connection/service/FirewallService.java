package edu.catolica.api.mikrotik.connection.service;

import edu.catolica.api.mikrotik.connection.infra.MikroTikApiClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class FirewallService {
    private final MikroTikApiClient mikroTikApiClient;
    private final String blockListName = "block_sites";
    private final String[] initialRegexpPattern = new String[]{"^.+(", ").*$"};

    @PostConstruct
    private void criarListaDeBloqueios() throws MikrotikApiException {
        if (encontrarListaDeBloqueios() != null)
            return;

        var comandoCriarNovo = String.format(
                "/ip/firewall/layer7-protocol/add name=%s regexp=\"%s\"",
                this.blockListName,
                String.join("", initialRegexpPattern)
        );
        mikroTikApiClient.getConnection().execute(comandoCriarNovo);
    }

    public void adicionarBloqueio(String name) throws MikrotikApiException {
        var listaDeBloqueios = encontrarListaDeBloqueios();
        var regexp = listaDeBloqueios.get("regexp");

        if (!regexp.contains(initialRegexpPattern[0]) || !regexp.contains(initialRegexpPattern[1]))
            return;

        var nameBlock = regexp.contentEquals(String.join("", initialRegexpPattern)) ? name : name + "|";
        regexp = String.valueOf(new StringBuilder(regexp).insert(initialRegexpPattern[0].length(), nameBlock));
        var comandoAdicionar = String.format("/ip/firewall/layer7-protocol/set .id=%s regexp=\"%s\"", listaDeBloqueios.get(".id"), regexp);

        mikroTikApiClient.getConnection().execute(comandoAdicionar);
    }

    public void removerBloqueio(String name) {

    }

    public void atualizarBloqueio(String name) {

    }

    private Map<String, String> encontrarListaDeBloqueios() throws MikrotikApiException {
        var comandoPesquisar = String.format("/ip/firewall/layer7-protocol/print where name=%s", this.blockListName);
        var resultadoPesquisa = mikroTikApiClient.getConnection().execute(comandoPesquisar);

        if (resultadoPesquisa.isEmpty())
            return null;

        return resultadoPesquisa.getFirst();
    }
}
