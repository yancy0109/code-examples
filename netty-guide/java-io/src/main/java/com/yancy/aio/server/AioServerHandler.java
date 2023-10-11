package com.yancy.aio.server;

import com.yancy.aio.ChannelAdapter;
import com.yancy.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author yancy0109
 * @date: 2023/10/7
 */
public class AioServerHandler extends ChannelAdapter {

    public AioServerHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("AioServer 与 Client 链接建立, 链接报告信息 : " + ctx.getChannel().getRemoteAddress());
            // 通知客户端链接建立成功
            ctx.writeAndFlush(
                    "服务端已建立链接成功 " + new Date() + " " + ctx.getChannel().getRemoteAddress() + "\r\n"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("服务端收到 : " + new Date() + " " + msg);
        // ctx.writeAndFlush("服务端信息处理 Success! \r\n");
    }
}
