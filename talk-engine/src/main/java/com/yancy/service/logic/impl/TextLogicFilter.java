package com.yancy.service.logic.impl;

import com.yancy.service.logic.LogicFilterBase;
import com.yancy.service.mode.BehaviorMatter;

/**
 * 逻辑根节点
 * @author yancy0109
 * @date: 2023/8/10
 */
public class TextLogicFilter extends LogicFilterBase {



    public String filter(BehaviorMatter request) {
        return "嗨，你好，如果想测试抽奖功能，请回复 抽奖";
    }

}
