package com.gear.dev.nett.ws.client.core;

import java.util.Objects;

/**
 * ClientData POJO - representa apenas as propriedades de conexão de um cliente.
 *
 * @author guerinorodella
 */
public class ClientData {

    private int id;
    private String name;
    private String connectionUrl;
    private String token;
    private String region;

    public ClientData(int id, String name, String token, String region, String connectionUrl) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.region = region;
        this.connectionUrl = connectionUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData data = (ClientData) o;
        return id == data.id && Objects.equals(name, data.name) && Objects.equals(connectionUrl, data.connectionUrl) && Objects.equals(token, data.token) && Objects.equals(region, data.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, connectionUrl, token, region);
    }

    @Override
    public String toString() {
        return """
                {"id = %s, name = %s}""".formatted(id, name);
    }
}
