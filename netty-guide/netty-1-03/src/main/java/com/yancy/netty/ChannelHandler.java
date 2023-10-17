package com.yancy.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author yancy0109
 * @date: 2023/10/16
 */
public class ChannelHandler {

    // 用于存放用户 Channel 信息, 也可以建立 map 结构模拟不同的消息群
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
