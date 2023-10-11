package com.yancy.nio;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.LinkedList;

/**
 * @author yancy0109
 * @date: 2023/10/9
 */
public class ChannelHandler {


    private SocketChannel socketChannel;

    private Charset charset;

    private LinkedList<String> writeCache = new LinkedList<>();

    public ChannelHandler(SocketChannel socketChannel, Charset charset) {
        this.socketChannel = socketChannel;
        this.charset = charset;
    }

    public SocketChannel channel() {
        return socketChannel;
    }

    public void writeAndFlush(String input) {
        System.out.println("Send : " + input);
        try {
            this.socketChannel.write(
                    this.charset.encode(input)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
