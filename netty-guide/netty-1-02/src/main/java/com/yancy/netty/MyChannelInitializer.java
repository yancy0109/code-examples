package com.yancy.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author yancy0109
 * @date: 2023/10/16
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 解码器
        ch.pipeline().addLast(
                // 基于指定字符串 当前等同于 LineBasedFrameDecoder
                // new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter())
                // 基于最大长度
                // new FixedLengthFrameDecoder(4)
                // 基于换行符号
                new LineBasedFrameDecoder(1024)
        ).addLast(
                // 解码转 String
                new StringDecoder(Charset.forName("GBK"))
        ).addLast(
                // String转字节
                new StringEncoder(Charset.forName("GBK"))
                )
        .addLast(
                // 添加接收数据实现方法
                new MyServerHandler()
        );
    }
}
