package com.yancy.service.logic;


import com.yancy.service.mode.LogicLine;

import java.util.List;

/**
 * 消息逻辑组件基础实现
 * @author yancy0109
 * @date: 2023/8/10
 */
public abstract class LogicFilterBase implements LogicFilter{

    private List<LogicLine> logicLineList;

    public List<LogicLine> getLogicLineList() {
        return logicLineList;
    }

    public void setLogicLineList(List<LogicLine> logicLineList) {
        this.logicLineList = logicLineList;
    }

}
