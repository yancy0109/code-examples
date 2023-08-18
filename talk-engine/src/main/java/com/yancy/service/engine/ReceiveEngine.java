package com.yancy.service.engine;

import com.yancy.service.mode.BehaviorMatter;

/**
 * 消息引擎接口
 * @author yancy0109
 */
public interface ReceiveEngine {

    /**
     * 过滤接口
     * @param request       请求内容
     * @return              处理结果
     */
    String process(final BehaviorMatter request);

}
