package com.gear.dev.nett.ws.client.core;

import java.util.Objects;

/**
 * ClientData POJO - representa apenas as propriedades de conexão de um cliente.
 *
 * @author guerinorodella
 */
public class ClientData {

    private String host;
    private int port;
    private String token;
    private String region;
    private String appKey;

    public ClientData() {
    }

    public ClientData(String host, int port, String token, String region) {
        this.host = host;
        this.port = port;
        this.token = token;
        this.region = region;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return port == that.port && Objects.equals(host, that.host) && Objects.equals(token, that.token) && Objects.equals(region, that.region) && Objects.equals(appKey, that.appKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, token, region, appKey);
    }
}
