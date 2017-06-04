package com.edu.nju.asi.utilities.enums;

/**
 * Created by cuihua on 2017/6/3.
 * <p>
 * 邮件的通知类型
 */
public enum MailNotificationType {

    // 保存、更新、删除
    SAVE("保存"),
    UPDATE("更新"),
    DELETE("删除");

    private String repre;

    private MailNotificationType(String repre) {
        this.repre = repre;
    }

    public String getRepre() {
        return repre;
    }

}
