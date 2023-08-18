package com.yancy.service.engine.impl;

import com.yancy.service.engine.EngineBase;
import com.yancy.service.logic.LogicFilter;
import com.yancy.service.mode.BehaviorMatter;
import com.yancy.service.mode.LogicLine;

import java.util.List;

/**
 * 消息引擎实现类
 * @author yancy0109
 * @date: 2023/8/10
 */
public class ReceiveEngineImpl extends EngineBase {

    @Override
    public String process(BehaviorMatter request) {
        // 获取到消息类型对应逻辑处理类
        LogicFilter logicFilter = router(request);
        // 如果当前节点没有后续节点, 说明单次消息返回内容
        if (null == logicFilter.getLogicLineList()) {
            return logicFilter.filter(request);
        }
        // 为连续型消息节点
        String openId = request.getOpenId();
        // 尝试获取上次触发过内容的节点
        LogicFilter continuedLogicFilter = continueTalk(openId);
        // 如果为刚开始对话
        if (null == continuedLogicFilter) {
            // 记录当前对话内容
            saveTalk(openId, logicFilter);
            return logicFilter.filter(request);
        }
        // 继续未完成的对话
        // 对话在进行中, 尝试根据回复信息找到下一个对话消息逻辑节点
        List<LogicLine> logicLines = continuedLogicFilter.getLogicLineList();
        LogicFilter nextNode = null;
        // 遍历查询		TODO 这段逻辑似乎还是应该交给 LogicFilter 实现更好！！
        for (LogicLine logicLine : logicLines) {
            if (request.getContent().contains(logicLine.getAccessSign())) {
                nextNode = logicLine.getNextNode();
                break;
            }
        }
        if (nextNode == null) {
            return "对话消息引擎执行错误, 请按照提示内容重新回复";
        }
        // 获取当前节点回复的消息
        String backMsg = nextNode.filter(request);
        // 如果当前回复节点已抵达对话逻辑末尾, 清除对话缓存信息
        // 否则进行对话信息保存
        if (null == nextNode.getLogicLineList()) {
            removeTalk(openId);
        } else {
            saveTalk(openId, nextNode);
        }
        return backMsg;
    }
}
