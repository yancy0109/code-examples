package com.yancy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author yancy0109
 * @date: 2023/10/19
 */
public class MyEncoder extends MessageToByteEncoder<String> {



    private static final int HEAD_MARK = 0x02;

    private static final int END_MARK = 0x03;

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        byte[] bytes = msg.getBytes();

        byte[] send = new byte[bytes.length + 1];
        // 拷贝
        System.arraycopy(bytes, 0, send, 1, bytes.length);
        send[send.length-1] = END_MARK;
        out.writeByte(HEAD_MARK);
        out.writeInt(send.length);
        out.writeBytes(send);
    }
}
