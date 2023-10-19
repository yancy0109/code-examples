package com.yancy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author yancy0109
 * @date: 2023/10/19
 */
public class MyDecoder extends ByteToMessageDecoder {

    /**
     * 数据包基础长度
     */
    private static final int BASE_LENGTH = 4;


    private static final int HEAD_MARK = 0x02;
    private static final int END_MARK = 0x03;


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 长度不足 return
        if (in.readableBytes() < BASE_LENGTH) {
            return;
        }

        int beginIdx;   // 记录包头位置
        while (true) {
            // 获取包头开始Idx
            beginIdx = in.readerIndex();
            // 标记包头开始Idx
            in.markReaderIndex();
            // 如果读到了协议Head Mark
            if (in.readByte() == HEAD_MARK) {
                break;
            }
            // 略过非Head字节 检查数据包长度
            // 不满足 return 等待数据到达
            if (in.readableBytes() < BASE_LENGTH) {
                return;
            }
        }
        // 读取Head后 检查剩余长度
        // 检查剩余长度
        int readableCount = in.readableBytes();
        if (readableCount <= 1) {
            // 重置读Idx 等待再次读取
            in.readerIndex(beginIdx);
            return;
        }

        // 长度域占4字节 读取int
        ByteBuf byteBuf = in.readBytes(1);
        String msgLengthStr = byteBuf.toString(Charset.forName("GBK"));
        int msgLen = Integer.parseInt(msgLengthStr);

        // 剩余长度不足可读取数量[不足结尾标识]
        readableCount = in.readableBytes();
        if (readableCount < msgLen + 1) {
            in.readerIndex(beginIdx);
            return;
        }

        ByteBuf content = in.readBytes(msgLen);

        // 读取结尾标识符
        byte end = in.readByte();
        if (end != END_MARK) {
            in.readerIndex(beginIdx);
            return;
        }

        out.add(content.toString(Charset.forName("GBK")));
    }

}
