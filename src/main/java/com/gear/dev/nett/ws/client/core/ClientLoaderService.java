package com.gear.dev.nett.ws.client.core;

import java.util.List;

/**
 * @author guerinorodella
 */
public interface ClientLoaderService {

    List<ClientData> loadAll(String connectionUrl);
}
