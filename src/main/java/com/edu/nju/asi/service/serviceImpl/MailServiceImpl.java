package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.infoCarrier.MailInfo;
import com.edu.nju.asi.service.MailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by cuihua on 2017/6/3.
 */
@Service("MailService")
public class MailServiceImpl implements MailService {

    // 系统发件人的账户密码
    private final String senderName = "fdfjj0407";
    private final String password = "qazxswedc123456";

    public MailServiceImpl() {
    }

    @Override
    public boolean notify(MailInfo mailInfo) throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");                    // 开启debug调试
        props.setProperty("mail.smtp.auth", "true");                // 发送服务器需要身份验证
        props.setProperty("mail.host", "smtp.163.com");             // 设置邮件服务器主机名
        props.setProperty("mail.transport.protocol", "smtp");       // 发送邮件协议名称

        Session session = Session.getInstance(props);

        boolean creatorResult = notifyOne(session, mailInfo, mailInfo.creator, true);

        boolean subscribersResult;
        if (mailInfo.subscribers == null) subscribersResult = true;
        else {
            boolean allTrue = true;
            for (String nowSubscriber : mailInfo.subscribers) {
                boolean nowResult = notifyOne(session, mailInfo, nowSubscriber, false);
                if (nowResult == false) {
                    allTrue = false;
                    break;
                }
            }
            subscribersResult = allTrue;
        }

        return creatorResult && subscribersResult;
    }

    private boolean notifyOne(Session session, MailInfo mailInfo, String nowUser, boolean isCreator) throws MessagingException {
        String action;
        if (isCreator) action = "创建";
        else action = "订阅";

        Message msg = new MimeMessage(session);
        msg.setSubject("asi");

        StringBuffer mailContent = new StringBuffer();
        mailContent.append("亲爱的asiquantour用户你好！\n\n");
        mailContent.append("您" + action + "的策略" + mailInfo.strategyID + "已" + mailInfo.type.getRepre() + "！详情请查看" + mailInfo.url + "\n\n");
        mailContent.append("asiquantour感谢您一直以来的支持，谢谢！");
        msg.setText(mailContent.toString());

        msg.setFrom(new InternetAddress(senderName + "@163.com"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(nowUser));

        System.out.println(mailContent.toString());

        Transport transport = session.getTransport();
        transport.connect("smtp.163.com", senderName, password);
        transport.sendMessage(msg, new Address[]{new InternetAddress(nowUser)});
        transport.close();
        return true;
    }
}
