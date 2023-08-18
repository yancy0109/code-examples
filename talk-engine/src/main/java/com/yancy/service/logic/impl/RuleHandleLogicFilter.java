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
public class RuleHandleLogicFilter extends LogicFilterBase {


    private Logger logger = LoggerFactory.getLogger(RuleHandleLogicFilter.class);

    public String filter(BehaviorMatter request) {
        String content = request.getContent();
        String gender = content.contains("women") ? "women" : "man";
        String age;
        try {
            age = content.substring(content.length() - 3, content.length() - 1);
            if (!age.matches("[0-9]+")){
                throw new Exception();
            }
        }catch (Exception e) {
            return "讲道理, 我应该提醒你格式不对, 然后保存你的对话状态让你重发, 可是好麻烦啊, 我直接拿字符串分割保存的你的信息正则判断, 你重新来过吧";
        }
        logger.info("已收到规则信息 用户:{}, gender:{}, age:{}", request.getOpenId(), gender, age);
        return "恭喜💐 您已中奖：" + "Mac" + "抽奖测试";

    }

}
