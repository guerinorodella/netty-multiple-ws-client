package com.gear.dev.nett.ws.client.core;

import com.gear.dev.nett.ws.client.core.data.ClientLoginRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação serviço responsável por carregar os dados dos clientes.
 *
 * @author guerinorodella
 */
@Service
public class ClientLoaderServiceImpl implements ClientLoaderService {

    private final ClientLoginRepository repository;

    @Inject
    public ClientLoaderServiceImpl(ClientLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientData> loadAll(String connectionUrl) {
        var clientLoginList = repository.loadAllActiveClients();
        if (clientLoginList == null || clientLoginList.isEmpty()) {
            return Collections.emptyList();
        }

        return clientLoginList.stream()
                .map(login -> new ClientData(
                        login.getId(),
                        login.getName(),
                        login.getToken(),
                        login.getRegion(),
                        connectionUrl))
                .collect(Collectors.toList());
    }

}
