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
    private final URLCreator urlCreator;

    @Inject
    public ClientLoaderServiceImpl(ClientLoginRepository repository, URLCreator urlCreator) {
        this.repository = repository;
        this.urlCreator = urlCreator;
    }

    @Override
    public List<ClientData> loadAll() {
        var clientLoginList = repository.loadAllActiveClients();
        if (clientLoginList == null || clientLoginList.isEmpty()) {
            return Collections.emptyList();
        }

        return clientLoginList.stream()
                .map(login -> new ClientData(
                        urlCreator.createDefault(login.getRegion()),
                        urlCreator.getDefaultPort(),
                        login.getToken(),
                        login.getAppKey()))
                .collect(Collectors.toList());
    }

}
