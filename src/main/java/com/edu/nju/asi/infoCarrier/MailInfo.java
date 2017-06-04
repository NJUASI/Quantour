package com.edu.nju.asi.infoCarrier;

import com.edu.nju.asi.utilities.enums.MailNotificationType;

import java.util.List;

/**
 * Created by cuihua on 2017/6/3.
 *
 * 策略更新时向用户发送邮件时的所需信息
 */
public class MailInfo {

    private final static String asiUrl = "119.23.223.62";

    // 创建者
    public String creator;

    // 订阅者列表
    public List<String> subscribers;

    // 发送邮件的原因
    public MailNotificationType type;

    // 发送邮件的源策略
    public String strategyID;

    // 发送邮件的源策略网址
    public String url;

    public MailInfo(String creator, List<String> subscribers, MailNotificationType type, String strategyID) {
        this.creator = creator;
        this.subscribers = subscribers;
        this.type = type;
        this.strategyID = strategyID;
        this.url = "/strategy/" + strategyID;
    }
}
