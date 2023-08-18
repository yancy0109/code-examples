package com.yancy.service.mode;

import java.util.Date;

/**
 * 消息类型
 * @author yancy0109
 * @date: 2023/8/9
 */
public class BehaviorMatter {

    private String openId;

    private String fromUserName;

    private String msgType;

    private String content;

    private String event;

    private Date createTime;

    public BehaviorMatter(String openId, String msgType, String content, String event) {
        this.openId = openId;
        this.msgType = msgType;
        this.content = content;
        this.event = event;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
