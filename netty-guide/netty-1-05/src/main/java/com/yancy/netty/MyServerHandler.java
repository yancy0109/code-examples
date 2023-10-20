package com.yancy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

/**
 * @author yancy0109
 * @date: 2023/10/19
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            System.out.println("URI: " + request.uri());
            System.err.println(msg);
        }

        if (msg instanceof HttpContent) {
            LastHttpContent content = (LastHttpContent) msg;
            ByteBuf byteBuf = content.content();
            if (byteBuf.isReadable()) {
                // 接收 msg 消息
                byte[] msgBytes = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(msgBytes);
                System.out.println(new String(msgBytes, StandardCharsets.UTF_8));

            }

            String sendMsg = "Welcome! This is http server base on Netty.";
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(
                            sendMsg.getBytes(StandardCharsets.UTF_8)
                    )
            );
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.write(response);
            ctx.flush();
        }

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
