package com.yancy.aio;

import com.yancy.aio.server.AioServer;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author yancy0109
 * @date: 2023/10/6
 */
public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {


    abstract protected void initChannel(AsynchronousSocketChannel channel);

    @Override
    public void completed(AsynchronousSocketChannel result, AioServer attachment) {
        try {
            System.out.println("Aio Server Connect with : " + result.getRemoteAddress());
            initChannel(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.getStackTrace();
    }
}
