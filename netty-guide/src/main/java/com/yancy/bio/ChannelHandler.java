package com.yancy.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author yancy0109
 * @date: 2023/10/8
 */
public class ChannelHandler {

    private Socket socket;

    private Charset charset;


    public ChannelHandler(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
    }

    public Socket socket() {
        return socket;
    }

    public Charset charset() {
        return charset;
    }

    public void writeAndFlush(String input) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(this.charset.encode(input).array());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
