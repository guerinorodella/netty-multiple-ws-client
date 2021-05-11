package com.gear.dev.nett.ws.client.infra;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guerinorodella
 */
public class Util {
    private static SimpleDateFormat sdf;

    public static String currentDateTime() {
        return getSDF().format(new Date());
    }

    private static SimpleDateFormat getSDF() {
        if (sdf != null) {
            return sdf;
        }
        sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return getSDF();
    }
}
