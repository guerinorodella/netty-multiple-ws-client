package com.gear.dev.nett.ws.client.core;

import com.gear.dev.nett.ws.client.core.data.ClientLogin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guerinorodella
 */
public class ClientDataFactory {

    private static final String DEFAULT_HOST = "wss://echo.websocket.org";

    private List<ClientData> instance;
    private String host;

    private ClientDataFactory(String host) {
        this.instance = new ArrayList<>();
        this.host = host;
    }

    public static ClientDataFactory create() {
        return create(DEFAULT_HOST);
    }

    public static ClientDataFactory create(String host) {
        return new ClientDataFactory(host);
    }

    public ClientDataFactory from(List<ClientLogin> clientLoginList) {
        instance =  clientLoginList.stream()
                .map(login -> new ClientData(
                        login.getId(),
                        login.getName(),
                        login.getToken(),
                        login.getRegion(),
                        host))
                .collect(Collectors.toList());
        return this;
    }

    public List<ClientData> build() {
        return instance;
    }
}
