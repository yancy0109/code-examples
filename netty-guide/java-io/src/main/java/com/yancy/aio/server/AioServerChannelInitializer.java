package com.yancy.aio.server;

import com.yancy.aio.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author yancy0109
 * @date: 2023/10/6
 */
public class AioServerChannelInitializer extends ChannelInitializer {


    @Override
    protected void initChannel(AsynchronousSocketChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer,
                10, TimeUnit.SECONDS,
                buffer,
                new AioServerHandler(channel, Charset.forName("GBK")));
    }


}
