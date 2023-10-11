package com.yancy.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yancy0109
 * @date: 2023/10/9
 */
public abstract class ChannelAdapter implements Runnable {

    private Selector selector;

    private Charset charset;

    private ChannelHandler channelHandler;

    public ChannelAdapter(Selector selector, Charset charset) {
        this.selector = selector;
        this.charset = charset;
    }

    @Override
    public void run() {
        // 循环交由 Selector不停poll事件进行处理
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                SelectionKey key;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 处理链接
     * @param key               操作链接对象
     * @throws IOException      IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }

        // 处理Client SocketChannel
        Class<?> superclass = key.channel().getClass().getSuperclass();
        if (superclass == SocketChannel.class) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            // SocketChannel 可连接
            if (key.isConnectable()) {
                // 如果 Channel 完成链接, 继续注册可读事件
                if (socketChannel.finishConnect()) {
                    channelHandler = new ChannelHandler(socketChannel, charset);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    channelActive(channelHandler);
                } else {
                    System.exit(1);
                }
            }
        }
        // 处理Server SocketChannel
        if (superclass == ServerSocketChannel.class)  {
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                SocketChannel acceptedSocketChannel = serverSocketChannel.accept();
                acceptedSocketChannel.configureBlocking(false);
                acceptedSocketChannel.register(selector, SelectionKey.OP_READ);
                this.channelHandler = new ChannelHandler(acceptedSocketChannel, this.charset);
                channelActive(channelHandler);
            }
         }
        // 可读事件
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(readBuffer);
            // System.out.println("read Byte :: " + read);
            if (read > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                channelRead(channelHandler, new String(bytes, charset));
            }
        }
    }

    public abstract void channelActive(ChannelHandler channelHandler);


    public abstract void channelRead(ChannelHandler ctx, String input);

}
