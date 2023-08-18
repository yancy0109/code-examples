package com.yancy.service.engine;

import com.yancy.service.logic.LogicFilter;
import com.yancy.service.logic.impl.*;
import com.yancy.service.mode.LogicLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息引擎配置
 * @author yancy0109
 * @date: 2023/8/10
 */
public class EngineConfig {

    /**
     * 存储用户回复信息处理节点 <openId, logicFilter>
     */
    protected Map<String, LogicFilter> receiveCache = new HashMap<String, LogicFilter>();

    /**
     * 存储不同消息类型LogicFilter根节点   <MsgType, LogicFilter>
     */
    protected Map<String, LogicFilter> rootLogicFilterGroup = new HashMap<String, LogicFilter>();

    /**
     * 组装消息引擎节点
     */
    public void init() {
        // 消息回复类型节点
        TextLogicFilter textLogicFilter = new TextLogicFilter();
        ArrayList<LogicLine> textLogicLines = new ArrayList<LogicLine>();
        LotteryLogicFilter lotteryLogicFilter = new LotteryLogicFilter();
        OtherLogicFilter otherLogicFilter = new OtherLogicFilter();
        textLogicLines.add(new LogicLine("抽奖", lotteryLogicFilter));
        textLogicLines.add(new LogicLine("", otherLogicFilter));
        textLogicFilter.setLogicLineList(textLogicLines);

        // 配置抽奖回复规则树
        ArrayList<LogicLine> lotteryLogicLine = new ArrayList<LogicLine>();
        lotteryLogicLine.add(new LogicLine("正常", new NormalLotteryLogicFilter()));
        RuleLotteryLogicFilter ruleLotteryLogicFilter = new RuleLotteryLogicFilter();
        lotteryLogicLine.add(new LogicLine("规则", ruleLotteryLogicFilter));
        lotteryLogicFilter.setLogicLineList(lotteryLogicLine);

        // 规则抽奖后续节点
        ArrayList<LogicLine> ruleLotteryLogicLine = new ArrayList<LogicLine>();
        ruleLotteryLogicLine.add(new LogicLine("", new RuleHandleLogicFilter()));

        ruleLotteryLogicFilter.setLogicLineList(ruleLotteryLogicLine);

        rootLogicFilterGroup.put("text", textLogicFilter);
        rootLogicFilterGroup.put("event", new EventLogicFilter());
    }

}
