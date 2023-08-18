package com.yancy.service.logic.impl;

import com.yancy.service.logic.LogicFilterBase;
import com.yancy.service.mode.BehaviorMatter;

/**
 * 逻辑根节点
 * @author yancy0109
 * @date: 2023/8/10
 */
public class RuleLotteryLogicFilter extends LogicFilterBase {



    public String filter(BehaviorMatter request) {
        return "请回复您的规则关键字, 按照如下规则进行复制修改后回复, 我会对您的信息进行拆分, eg:{gender:man,age:18}";
    }

}
