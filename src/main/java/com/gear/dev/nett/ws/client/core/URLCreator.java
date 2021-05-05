package com.gear.dev.nett.ws.client.core;

import org.springframework.stereotype.Component;

/**
 * @author guerinorodella
 */
@Component
public class URLCreator {
    private static final String HOST = "INSIRA_AQUI_O_PREFIXO_DO_HOST";
    private static final String FINAL_PATH = "SE_HOUVER_COLOQUE_O_RESTANTE_DO_PATH";
    private static final String WSS = "wss//";
    private static final int PORT_NUM = 8080;

    public String createDefault(String region) {
        return WSS + region + "-" + HOST + ":" + PORT_NUM + "/" + FINAL_PATH;
    }

    public int getDefaultPort() {
        return PORT_NUM;
    }
}
