package com.yancy.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * @author yancy0109
 * @date: 2023/10/6
 */
public class ChannelHandler {

    private AsynchronousSocketChannel channel;

    private Charset charset;

    public ChannelHandler(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
    }

    public AsynchronousSocketChannel getChannel() {
        return channel;
    }

    public void setChannel(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public void writeAndFlush(String msg) {
        ByteBuffer byteBuffer = this.charset.encode(msg);
        channel.write(byteBuffer, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("发送消息成功");
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("发送消息失败");
            }
        });
    }

}
