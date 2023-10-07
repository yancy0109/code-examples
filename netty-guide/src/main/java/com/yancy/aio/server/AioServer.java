package com.yancy.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author yancy0109
 * @date: 2023/10/6
 */
public class AioServer extends Thread {

    private AsynchronousServerSocketChannel serverSocketChannel;

    @Override
    public void run() {
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open(
                    AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10)
            );
            serverSocketChannel.bind(new InetSocketAddress(7379));
            System.out.println("Server Start Done, Over :" + serverSocketChannel.getLocalAddress().toString());
            // 等待
            CountDownLatch latch = new CountDownLatch(1);
            serverSocketChannel.accept(this, new AioServerChannelInitializer());
            latch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AsynchronousServerSocketChannel serverSocketChannel() {
        return serverSocketChannel;
    }

    public static void main(String[] args) {
        new AioServer().start();
    }
}
