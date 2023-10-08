package com.yancy.bio.client;

import com.yancy.bio.ChannelAdapter;
import com.yancy.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yancy0109
 * @date: 2023/10/7
 */
public class BioClientHandler extends ChannelAdapter {

    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("链接报告 LocalAddress: " + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("Hi, 我是 BioClient \r\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object input) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息: " + input);
        ctx.writeAndFlush("BioClient has received server Msg");
    }
}
