package com.yancy.nio.client;

import com.yancy.nio.ChannelAdapter;
import com.yancy.nio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yancy0109
 * @date: 2023/10/9
 */
public class NioClientHandler extends ChannelAdapter {

    public NioClientHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("链接报告 LocalAddress: " + ctx.channel().getLocalAddress());
            ctx.writeAndFlush("Hi, 我是 NIOClient \r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void channelRead(ChannelHandler ctx, String input) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息: " + input);
        ctx.writeAndFlush("NIOClient has received server Msg");
    }
}
