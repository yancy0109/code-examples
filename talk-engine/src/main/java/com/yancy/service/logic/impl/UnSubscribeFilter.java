package com.yancy.service.logic.impl;

import com.yancy.service.logic.LogicFilterBase;
import com.yancy.service.mode.BehaviorMatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 取消关注节点
 * @author yancy0109
 * @date: 2023/8/10
 */
public class UnSubscribeFilter extends LogicFilterBase {


    private Logger logger = LoggerFactory.getLogger(UnSubscribeFilter.class);

    public String filter(BehaviorMatter request) {

        logger.info("用户{} 已取消关注", request.getOpenId());
        return null;
    }

}
