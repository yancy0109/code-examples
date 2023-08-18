package com.yancy.service.logic.impl;

import com.yancy.service.logic.LogicFilterBase;
import com.yancy.service.mode.BehaviorMatter;

/**
 * 逻辑根节点
 * @author yancy0109
 * @date: 2023/8/10
 */
public class LotteryLogicFilter extends LogicFilterBase {



    public String filter(BehaviorMatter request) {
        return "请选择抽奖模式, 正常 or 规则, 请按照关键字进行回复";
    }

}
