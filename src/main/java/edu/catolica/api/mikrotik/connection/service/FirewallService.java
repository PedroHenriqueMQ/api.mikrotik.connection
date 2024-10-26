package edu.catolica.api.mikrotik.connection.service;

import edu.catolica.api.mikrotik.connection.infra.MikroTikApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FirewallService {
    private final MikroTikApiClient mikroTikApiClient;

    public void adicionarBloqueio(String name) {

    }

    public void removerBloqueio(String name) {

    }

    public void atualizarBloqueio(String name) {

    }

    private List<Map<String, String>> encontrarBloqueio(String name) {
        return null;
    }
}
