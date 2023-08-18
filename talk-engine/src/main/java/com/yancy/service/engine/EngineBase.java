package com.yancy.service.engine;


import com.yancy.service.logic.LogicFilter;
import com.yancy.service.mode.BehaviorMatter;

/**
 * 消息逻辑引擎基础实现
 * @author yancy0109
 * @date: 2023/8/10
 */
public abstract class EngineBase extends EngineConfig implements ReceiveEngine {

    /**
     * 根据openId，尝试继续对话，如果不存在记录，则返回对话根节点
     * @param openId            用户openId
     * @return                  logicFilter
     */
    public LogicFilter continueTalk(String openId) {
        return receiveCache.get(openId);
    }

    /**
     * 保存当前对话节点信息
     * @param openId            用户openId
     * @param logicFilter       logicFilter
     */
    public void saveTalk(String openId, LogicFilter logicFilter) {
        receiveCache.put(openId, logicFilter);
    }

    /**
     * 移除当前对话信息
     * @param openId        用户openId
     */
    public void removeTalk(String openId) {
        receiveCache.remove(openId);
    }

    /**
     * 路由到对应服务逻辑过滤器节点
     * @param request   用户请求
     * @return          logicFilter
     */
    public LogicFilter router(BehaviorMatter request) {
        // 根据消息类型获取过滤节点根节点
        return rootLogicFilterGroup.get(request.getMsgType());
    }

    public String process(BehaviorMatter request) {
        throw new RuntimeException("未实现消息引擎服务");
    }
}
