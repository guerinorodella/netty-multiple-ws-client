package com.gear.dev.nett.ws.client.app;

import com.gear.dev.nett.ws.client.di.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guerinorodella
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AppStarter appStarter = context.getBean(AppStarter.class);
        appStarter.start();
    }
}
