package com.yancy.netty.server;

import com.yancy.netty.codec.MyDecoder;
import com.yancy.netty.codec.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yancy0109
 * @date: 2023/10/19
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new MyDecoder())
                .addLast(new MyEncoder())
                .addLast(new MyServerHandler());
    }
}
