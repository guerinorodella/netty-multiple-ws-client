package com.gear.dev.nett.ws.client.infra.netty.handler;

import com.gear.dev.nett.ws.client.core.ClientData;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

import static io.netty.handler.codec.http.websocketx.WebSocketVersion.V13;

/**
 * @author guerinorodella
 */
@Component
public class WSClientHandlerFactory {
    public ChannelHandler create(ClientData clientData) {
        URI uri = URI.create(clientData.getConnectionUrl());
        var handshaker = WebSocketClientHandshakerFactory.newHandshaker(uri,
                V13,
                null,
                false,
                new DefaultHttpHeaders());

        return new WSChannelHandler(handshaker, clientData);
    }
}
