package com.yancy.service.logic.impl;

import com.yancy.service.logic.LogicFilterBase;
import com.yancy.service.mode.BehaviorMatter;

/**
 * 逻辑根节点
 * @author yancy0109
 * @date: 2023/8/10
 */
public class OtherLogicFilter extends LogicFilterBase {



    public String filter(BehaviorMatter request) {
        return "暂不支持其他文字操作类型";
    }

}
