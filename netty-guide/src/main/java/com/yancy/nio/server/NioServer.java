package com.yancy.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author yancy0109
 * @date: 2023/10/9
 */
public class NioServer {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private boolean bindSuccess = false;

    public NioServer bind(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(
                    selector, SelectionKey.OP_ACCEPT
            );
            System.out.println("NIO Server started done");
            bindSuccess = true;
            return this;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void run() {
        if (bindSuccess) {
            new NioServerHandler(selector, StandardCharsets.UTF_8).run();
        } else {
            throw new RuntimeException("Bind Port has error");
        }
    }

    public static void main(String[] args) {
        new NioServer().bind(11451).run();
    }
}
