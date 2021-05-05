package com.gear.dev.nett.ws.client.core.data;

import java.util.List;

/**
 * Esta interface define a conversa com o BD, a implementação desta é quem irá carregar a informação do BD.
 *
 * @author guerinorodella
 */
public interface ClientLoginRepository {
    List<ClientLogin> loadAllActiveClients();
}
