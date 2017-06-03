package com.edu.nju.asi.test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by cuihua on 2017/6/3.
 */
public class Test {

    public static void main(String[] args) throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");                    // 开启debug调试
        props.setProperty("mail.smtp.auth", "true");                // 发送服务器需要身份验证
        props.setProperty("mail.host", "smtp.163.com");             // 设置邮件服务器主机名
        props.setProperty("mail.transport.protocol", "smtp");       // 发送邮件协议名称

        Session session = Session.getInstance(props);
        Message msg = new MimeMessage(session);
        msg.setSubject("迭代三");
        msg.setText("今天晚上见");
        msg.setFrom(new InternetAddress("fdfjj0407@163.com"));

        Transport transport = session.getTransport();
        transport.connect("smtp.163.com", "fdfjj0407", "qazxswedc123456");
        transport.sendMessage(msg, new Address[]{new InternetAddress("151250037@smail.edu.nju.cn")});
        transport.close();

    }
}
