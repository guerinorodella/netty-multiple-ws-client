package com.gear.dev.nett.ws.client.core.data;

/**
 * Tipicamente esta classe seria uma entidade do BD, mas para fim de testes será apenas uma classe qualquer.
 *
 * @author guerinorodella
 */
public class ClientLogin {

    private int id;
    private String name;
    private String token;
    private String renewToken;
    private String region;
    private String appKey;
    private boolean connected;
    private boolean valid;

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

    public String getRenewToken() {
        return renewToken;
    }

    public void setRenewToken(String renewToken) {
        this.renewToken = renewToken;
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

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
