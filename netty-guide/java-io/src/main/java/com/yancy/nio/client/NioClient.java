package com.yancy.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * NIO Client
 * @author yancy0109
 * @date: 2023/10/9
 */
public class NioClient {

    private InetSocketAddress inetSocketAddress;

    private SocketChannel socketChannel;

    private Selector selector;

    private boolean isConnected = false;

    public NioClient() {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean connect(String addr, int port) {
        this.inetSocketAddress = new InetSocketAddress(addr, port);
        try {
            boolean connected = socketChannel.connect(this.inetSocketAddress);
            this.isConnected = connected;
            if (isConnected) {
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
            return connected;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        new NioClientHandler(selector, StandardCharsets.UTF_8).run();
    }

    public static void main(String[] args) {
        NioClient nioClient = new NioClient();
        nioClient.connect("localhost", 11451);
        nioClient.start();
    }

}
