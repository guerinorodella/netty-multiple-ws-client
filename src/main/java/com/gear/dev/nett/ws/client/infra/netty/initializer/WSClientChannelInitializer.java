package com.gear.dev.nett.ws.client.infra.netty.initializer;

import com.gear.dev.nett.ws.client.core.ClientData;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.URI;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Este Handler é quem 'configura o pipeline' do canal que será criado e executado pelo Netty na conexão WS.
 * Pense que nesta classe estarão apenas a inicialização de como será o canal do Netty, quais pipelines serão
 * adicionados, os decoders e encoders, os handler de evento Iddle entre outros.
 *
 * Este código foi implementado com o exemplo:
 * https://netty.io/4.0/xref/io/netty/example/http/websocketx/client/WebSocketClient.html
 *
 * @author guerinorodella
 */
public class WSClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    public static final int MAX_CONTENT_LENGTH = 8192;
    public static final int IGNORE = 0;
    public static final int THIRTY = 30;
    private final ClientData clientData;
    private final ChannelHandler channelHandler;

    public WSClientChannelInitializer(ClientData clientData, ChannelHandler channelHandler) {
        this.clientData = clientData;
        this.channelHandler = channelHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // Nesta linha está sendo criado um IddleStateHandler - Handler para estado ocioso, ou seja, quando o Netty
        // Identificar que nossa conexão está ociosa, irá gerar um IdleStateEvent ( Evento de Ociosidade )
        // E então quando ocorer este evento, nosso com.gear.dev.nett.ws.client.infra.netty.handler.WSChannelHandler#userEventTriggered
        // será chamado.
        // O Tempo para considerar ociosidade, é informado no construtor do IdleStateHandler. No nosso caso, são 30 segundos.
        // NOTA: idleStateHandler - deverá ser o PRIMEIRO handler a ser adicionado no pipeline.
        pipeline.addLast("idleStateHandler", new IdleStateHandler(IGNORE, THIRTY, IGNORE, SECONDS)); // Funciona como Keep-alive

        URI uri = URI.create(clientData.getConnectionUrl());
        boolean securityEnabled = isSecurityEnabled(uri);
        if (securityEnabled) {
            SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
            pipeline.addLast(sslContext.newHandler(ch.alloc(), uri.getHost(), uri.getPort()));
        }
        pipeline.addLast(new HttpClientCodec(),
                new HttpObjectAggregator(MAX_CONTENT_LENGTH),
                channelHandler); // por fim, nosso handler que irá interceptar as mensagens
    }

    private boolean isSecurityEnabled(URI uri) {
        String scheme = uri.getScheme() == null ? "ws" : uri.getScheme();
        return "wss".equalsIgnoreCase(scheme);
    }
}
