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
        bootstrap.group(eventGroup) // Grupo é configurado uma única vez!
                .channel(NioSocketChannel.class); // Canal também é configurado uma única vez;

        try {

            for (ClientData cliData : clientDataList) {
                addClient(cliData);
            }

        } catch (InterruptedException ignored) {
        }
    }

    public void addClient(ClientData cliData) throws InterruptedException {
        URI uri = URI.create(cliData.getConnectionUrl()); // Só pra facilitar a separação do host e porta

        // Esse Handler é quem de fato, cuida da conexão, dos pacotes recebidos, envio etc.
        // Ele está numa Factory porque tem que ser criado um novo para cada conexão
        var channelHandler = clientHandlerFactory.create(cliData);

        // o Channel Initializer é quem CONFIGURA o pipeline do canal - é o que é, inicializa o canal!
        var channelInitializer = wsInitializerFactory.create(cliData, channelHandler);

        bootstrap.handler(channelInitializer) // configura o handler
                .connect(uri.getHost(), uri.getPort()) // Instrução que de fato vai conectar
                .sync();
    }


}
