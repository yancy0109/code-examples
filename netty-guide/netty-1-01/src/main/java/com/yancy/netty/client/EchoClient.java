package com.yancy.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author yancy0109
 * @date: 2023/10/12
 */
public class EchoClient {

    private final String host;

    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建 Bootstrap
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)    // 指定NIOChannel
                    .remoteAddress(new InetSocketAddress(host, port))   // 指定服务器SocketAddress
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 在创建Channel时 添加 EchoClientHandler
                            ch.pipeline().addLast(
                                    new EchoClientHandler()
                            );
                        }
                    });
            // 连接远程节点, 阻塞至完成
            ChannelFuture f = b.connect().sync();
            // 阻塞直至Channel关闭
            f.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoop
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 11451;
        new EchoClient(host, port).start();
    }
}
