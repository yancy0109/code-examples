package com.yancy.service.logic;


import com.yancy.service.mode.BehaviorMatter;
import com.yancy.service.mode.LogicLine;

import java.util.List;

/**
 * 消息过滤逻辑接口
 * @author yancy0109
 */
public interface LogicFilter {

    /**
     * 当前节点回复消息
     */
    String filter(BehaviorMatter request);

    /**
     * 再接收消息应该回复的内容组件
     */
    List<LogicLine> getLogicLineList();
}
