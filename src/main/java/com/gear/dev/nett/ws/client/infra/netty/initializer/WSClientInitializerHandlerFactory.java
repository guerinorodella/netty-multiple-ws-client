package com.gear.dev.nett.ws.client.infra.netty.initializer;

import com.gear.dev.nett.ws.client.core.ClientData;
import io.netty.channel.ChannelHandler;
import org.springframework.stereotype.Component;

/**
 * @author guerinorodella
 */
@Component
public class WSClientInitializerHandlerFactory {

    public ChannelHandler create(ClientData cliData, ChannelHandler handler) {
        return new WSClientChannelInitializer(cliData, handler);
    }
}
