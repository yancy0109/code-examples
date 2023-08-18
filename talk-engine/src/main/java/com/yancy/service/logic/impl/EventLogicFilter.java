package com.yancy.service.logic.impl;

import com.yancy.service.logic.LogicFilter;
import com.yancy.service.logic.LogicFilterBase;
import com.yancy.service.mode.BehaviorMatter;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件逻辑节点
 * @author yancy0109
 * @date: 2023/8/10
 */
public class EventLogicFilter extends LogicFilterBase {

    private Map<String, LogicFilter> eventLogicFilterMap = new HashMap<String, LogicFilter>();

    public void initEventMap() {
        eventLogicFilterMap.put("subscribe", new SubscribeFilter());
        eventLogicFilterMap.put("unsubscribe", new UnSubscribeFilter());
    }



    /**
     * 事件逻辑处理通常为单次消息传递返回，并不需要组成消息逻辑树
     */
    public String filter(BehaviorMatter request) {
        String event = request.getEvent();
        LogicFilter logicFilter = eventLogicFilterMap.get(event);
        if (null == logicFilter) {
            return "暂未支持该种类事件";
        }
        return logicFilter.filter(request);
    }

}
