package com.gear.dev.nett.ws.client.infra.netty;

import com.gear.dev.nett.ws.client.core.ClientData;
import com.gear.dev.nett.ws.client.core.WSClientManager;
import com.gear.dev.nett.ws.client.infra.netty.handler.WSClientHandlerFactory;
import com.gear.dev.nett.ws.client.infra.netty.initializer.WSClientInitializerHandlerFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

/**
 * @author guerinorodella
 */
@Service
public class NettyWSClientManager implements WSClientManager {

    private final Bootstrap bootstrap;
    private final EventLoopGroup eventGroup;
    private final WSClientInitializerHandlerFactory wsInitializerFactory;
    private final WSClientHandlerFactory clientHandlerFactory;

    @Inject
    public NettyWSClientManager(WSClientInitializerHandlerFactory wsInitializerFactory,
                                WSClientHandlerFactory clientHandlerFactory) {
        this.wsInitializerFactory = wsInitializerFactory;
        this.clientHandlerFactory = clientHandlerFactory;
        bootstrap = new Bootstrap();
        eventGroup = new NioEventLoopGroup();
    }

    @Override
    public void start(List<ClientData> clientDataList) {
        bootstrap.group(eventGroup); // Esta instrução deve ser feita apenas 1 vez;

        try {

            for (ClientData cliData : clientDataList) {
                addClient(cliData);
            }

        } catch (InterruptedException ignored) {
        }
    }

    public void addClient(ClientData cliData) throws InterruptedException {
        URI uri = URI.create(cliData.getConnectionUrl());
        var channelHandler = clientHandlerFactory.create(cliData);
        bootstrap.channel(NioSocketChannel.class)
                .handler(wsInitializerFactory.create(cliData, channelHandler))
                .connect(uri.getHost(), uri.getPort())
                .sync();
//        handler.handshakeFuture().sync();
    }


}
