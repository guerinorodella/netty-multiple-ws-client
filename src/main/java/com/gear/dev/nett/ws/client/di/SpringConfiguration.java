package com.gear.dev.nett.ws.client.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author guerinorodella
 */
@Configuration
@ComponentScan({"com.gear.dev.netty.ws.client.core", "com.gear.dev.netty.ws.client.infra"})
public class SpringConfiguration {
}
