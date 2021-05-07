package com.gear.dev.nett.ws.client.infra.netty.handler;

import com.gear.dev.nett.ws.client.core.ClientData;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleStateEvent;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @author guerinorodella
 */
public class WSChannelHandler extends SimpleChannelInboundHandler<Object> {

    private final ClientData clientData;
    private final WebSocketClientHandshaker handshaker;
    private ChannelPromise handshakeFuture;

    public WSChannelHandler(WebSocketClientHandshaker handshaker, ClientData clientData) {
        this.clientData = clientData;
        this.handshaker = handshaker;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        handshakeFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        handshaker.handshake(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.printf("[WS-CLIENT][%s] - Desconectou", clientData.toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();

        // Faz o handshake para finalizar a conexão.
        if (!handshaker.isHandshakeComplete()) {
            try {
                handshaker.finishHandshake(ch, (FullHttpResponse) msg);
                handshakeFuture.setSuccess();
            } catch (WebSocketHandshakeException e) {
                System.out.printf("[WS-CLIENT][%s] - Falhou ao se conectar -> %s", clientData.toString(), e.getMessage());
                handshakeFuture.setFailure(e);
            }
            return;
        }

        // Não entendo muito bem essa parte
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            throw new IllegalStateException(
                    "Unexpected FullHttpResponse (getStatus=" + response.status().code() +
                            ", content=" + response.content().toString(UTF_8) + ')');
        }

        // Esta parte deveria ser feita com os Encoders e Decoders das mensagens;
        WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            System.out.printf("[WS-CLIENT][%s] - Recebido -> %s", clientData.toString(), textFrame.text());

        } else if (frame instanceof PongWebSocketFrame) {
            System.out.printf("[WS-CLIENT][%s] - Recebido -> Pong...", clientData.toString());

        } else if (frame instanceof CloseWebSocketFrame) {
            System.out.printf("[WS-CLIENT][%s] - Recebido -> Desconexão", clientData.toString());
            ch.close();
        }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.writeAndFlush(new PingWebSocketFrame());
        }
    }
}
