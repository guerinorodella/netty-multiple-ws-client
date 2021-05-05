package com.gear.dev.nett.ws.client.core.data;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

/**
 * @author guerinorodella
 */
public class ClientLoginFactory {

    private final List<ClientLogin> instance;

    public ClientLoginFactory() {
        instance = new ArrayList<>();
    }

    public static ClientLoginFactory create() {
        return new ClientLoginFactory();
    }


    public ClientLoginFactory addValid(int id, String desc, String region) {
        ClientLogin client = new ClientLogin();
        client.setId(id);
        client.setAppKey(randomUUID().toString());
        client.setToken(randomUUID().toString());
        client.setRenewToken(randomUUID().toString());
        client.setName(desc);
        client.setRegion(region);
        client.setValid(true);
        instance.add(client);
        return this;
    }

    public List<ClientLogin> build() {
        return instance;
    }
}
