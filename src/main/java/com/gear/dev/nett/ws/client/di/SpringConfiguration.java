package com.gear.dev.nett.ws.client.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author guerinorodella
 */
@Configuration
@ComponentScan({"com.gear.dev.nett.ws.client.core", "com.gear.dev.nett.ws.client.infra"})
public class SpringConfiguration {
}
