package com.yancy.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author yancy0109
 * @date: 2023/10/7
 */
public class BioClient {

    private Socket socket;

    public BioClient(String addr, int port) {
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        new BioClientHandler(this.socket, Charset.forName("GBK")).run();
    }

    public static void main(String[] args) {
        BioClient client = new BioClient("localhost", 11451);
        client.start();
    }
}
