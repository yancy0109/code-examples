package com.yancy.service.logic.impl;


import com.yancy.service.logic.LogicFilterBase;
import com.yancy.service.mode.BehaviorMatter;

/**
 * 关注逻辑
 * @author yancy0109
 * @date: 2023/8/10
 */
public class SubscribeFilter extends LogicFilterBase {



    public String filter(BehaviorMatter request) {

        return "感谢您的关注，哇卡卡卡";
    }

}
