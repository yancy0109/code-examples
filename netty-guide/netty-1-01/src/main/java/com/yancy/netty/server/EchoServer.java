package com.yancy.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author yancy0109
 * @date: 2023/10/12
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 11451;
        EchoServer echoServer = new EchoServer(port);
        echoServer.start();
    }

    public void start() throws InterruptedException {
        EchoServerHandler serverHandler = new EchoServerHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {

            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // 指定NIOChannel
                    .channel(NioServerSocketChannel.class)
                    // 指定端口套接字地址
                    .localAddress(new InetSocketAddress(this.port))
                    // 为当前 NioServerSocketChannel 添加 ChannelHandler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 为新建立 SocketChannel 添加 ChannelHandler
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            // 异步绑定服务器 sync阻塞至绑定完成
            ChannelFuture f = b.bind().sync();
            // 获取CloseFuture 阻塞至完成
            f.channel().closeFuture().sync();
        } finally {
            // 释放Group资源
            group.shutdownGracefully().sync();
        }
    }
}
