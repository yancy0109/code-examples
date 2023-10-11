package com.yancy.aio.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author yancy0109
 * @date: 2023/9/28
 */
public class AioClient {

    private AsynchronousSocketChannel socketChannel;

    public AioClient() {
        try {
            socketChannel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Future<Void> connect(String addr, int port) {
        return socketChannel.connect(
                new InetSocketAddress(addr, port)
        );
    }

    public void blockConnect(String addr, int port) {
        try {
            Future<Void> future = socketChannel.connect(
                    new InetSocketAddress(addr, port)
            );
            System.out.println("Aio Client Start Done");
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }



    public void read(ByteBuffer dst) {
        this.socketChannel.read(dst, dst,
                new AioClientHandler(socketChannel, Charset.forName("GBK")));
    }

    public static void main(String[] args) throws InterruptedException {
        AioClient aioClient = new AioClient();
        aioClient.blockConnect("localhost", 7379);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        aioClient.read(byteBuffer);
        Thread.sleep(100000);
    }
}
