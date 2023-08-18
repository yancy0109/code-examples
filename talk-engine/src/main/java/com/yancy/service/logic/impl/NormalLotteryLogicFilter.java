package com.yancy.service.logic.impl;

import com.yancy.service.logic.LogicFilterBase;
import com.yancy.service.mode.BehaviorMatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 逻辑根节点
 * @author yancy0109
 * @date: 2023/8/10
 */
public class NormalLotteryLogicFilter extends LogicFilterBase {


    private Logger logger = LoggerFactory.getLogger(NormalLotteryLogicFilter.class);


    public String filter(BehaviorMatter request) {
        logger.info("已收到抽奖请求 用户:{}", request.getOpenId());
        return "恭喜💐 您已中奖：" + "Mac" + " 抽奖测试";
    }

}
