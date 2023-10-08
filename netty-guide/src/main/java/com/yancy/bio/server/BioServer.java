package com.yancy.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author yancy0109
 * @date: 2023/10/8
 */
public class BioServer implements Runnable {

    private ServerSocket serverSocket;

    private InetSocketAddress socketAddress;

    public BioServer(int port) {
        this.socketAddress = new InetSocketAddress(port);
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(this.socketAddress);
            System.out.println("BioServer start done");
            while (true) {
                Socket socket = serverSocket.accept();
                BioServerHandler handler = new BioServerHandler(socket, Charset.forName("GBK"));
                // 开启线程处理与子线程的链接
                new Thread(handler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new BioServer(11451).run();
    }
}
