package com.gear.dev.nett.ws.client.core;

import com.gear.dev.nett.ws.client.core.data.ClientLogin;
import com.gear.dev.nett.ws.client.core.data.ClientLoginFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guerinorodella
 */
public class ClientDataFactory {

    private final URLCreator urlCreator;
    private List<ClientData> instance;

    public ClientDataFactory() {
        this.instance = new ArrayList<>();
        this.urlCreator = new URLCreator();
    }

    public static ClientDataFactory create() {
        return new ClientDataFactory();
    }

    public ClientDataFactory add(int id, String desc, String host) {
        return null;
    }

    public ClientDataFactory from(List<ClientLogin> clientLoginList) {
        instance =  clientLoginList.stream()
                .map(login -> new ClientData(
                        urlCreator.createDefault(login.getRegion()),
                        urlCreator.getDefaultPort(),
                        login.getToken(),
                        login.getAppKey()))
                .collect(Collectors.toList());
        return this;
    }

    public List<ClientData> build() {
        return instance;
    }
}
