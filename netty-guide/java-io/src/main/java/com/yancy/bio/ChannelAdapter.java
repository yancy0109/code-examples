package com.yancy.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author yancy0109
 * @date: 2023/10/7
 */
public abstract class ChannelAdapter implements Runnable {

    private Socket socket;

    private Charset charset;

    private ChannelHandler ctx;

    public ChannelAdapter(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
        while (!socket.isConnected()) {
            break;
        }
        this.ctx = new ChannelHandler(this.socket, this.charset);
        channelActive(this.ctx);
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                channelRead(ctx, this.charset.decode(ByteBuffer.wrap(str.getBytes())));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("读取结束");
    }

    /**
     * 链接通知抽象类
     */
    public abstract void channelActive(ChannelHandler ctx);

    /**
     * 读取消息抽象类
     */
    public abstract void channelRead(ChannelHandler ctx, Object input);

}
