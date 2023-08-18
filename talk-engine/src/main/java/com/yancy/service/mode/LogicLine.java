package com.yancy.service.mode;


import com.yancy.service.logic.LogicFilter;

/**
 * 消息过滤逻辑组件连接对象
 * @author yancy0109
 * @date: 2023/8/10
 */
public class LogicLine {

    /**
     * 通往下一个节点需要包含对话内容
     */
    private String accessSign;

    /**
     * 下一个对话节点
     */
    private LogicFilter nextNode;

    public LogicLine(String accessSign, LogicFilter nextNode) {
        this.accessSign = accessSign;
        this.nextNode = nextNode;
    }

    public String getAccessSign() {
        return accessSign;
    }

    public void setAccessSign(String accessSign) {
        this.accessSign = accessSign;
    }


    public LogicFilter getNextNode() {
        return nextNode;
    }

    public void setNextNode(LogicFilter nextNode) {
        this.nextNode = nextNode;
    }
}
