package com.yancy.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 消息处理器
 * @author yancy0109
 * @date: 2023/10/4
 */
public abstract class ChannelAdapter implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    private Charset charset;

    public ChannelAdapter(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
        if (channel.isOpen()) {
            channelActive(new ChannelHandler(channel, charset));
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        try {
            attachment.flip();
            channelRead(new ChannelHandler(channel, charset), charset.decode(attachment));
            attachment.clear();
            // 继续尝试读取
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            final long timeout = 60 * 60L;
//            channel.read(buffer, timeout, TimeUnit.SECONDS, buffer,
//                new CompletionHandler<Integer, ByteBuffer>() {
//                    @Override
//                    public void completed(Integer result, ByteBuffer attachment) {
//                        if (result == -1) {
//                            try {
//                                channelInactive(new ChannelHandler(channel, charset));
//                                channel.close();
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                            return;
//                        }
//                        buffer.flip();
//                        channelRead(new ChannelHandler(channel, charset), charset.decode(buffer));
//                        buffer.clear();
//                        channel.read(buffer, timeout, TimeUnit.SECONDS, null, this);
//                    }
//
//                    @Override
//                    public void failed(Throwable exc, ByteBuffer attachment) {
//                        exc.getStackTrace();
//                    }
//                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.getStackTrace();
    }


    public abstract void channelActive(ChannelHandler ctx);

    public abstract void channelInactive(ChannelHandler ctx);

    /**
     * 读取消息抽象接口
     * @param ctx   Channel上下文
     */
    public abstract void channelRead(ChannelHandler ctx, Object msg);
}
