package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.infoCarrier.MailInfo;
import com.edu.nju.asi.service.MailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
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
        return notifyCreator(session, mailInfo) && notifuSubscribers(session, mailInfo);
    }

    private boolean notifyCreator(Session session, MailInfo mailInfo) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(senderName + "@163.com"));
        msg.setSubject("迭代三问题");

//        StringBuffer mailContent = new StringBuffer();
//        mailContent.append("亲爱的asiquantour用户你好！\n");
//        mailContent.append("\t您创建的策略" + mailInfo.strategyID + "已" + mailInfo.type.getRepre() + "！详情请查看" + mailInfo.url + "\n");
//        mailContent.append("\n\nasiquantour感谢您一直以来的支持，谢谢！");
//        msg.setText(mailContent.toString());
        msg.setText("傻逼！");

        Transport transport = session.getTransport();
        transport.connect("smtp.163.com", senderName, "qazxswedc123456");
        transport.sendMessage(msg, new Address[]{new InternetAddress(mailInfo.creator)});
        transport.close();

        return true;
    }

    private boolean notifuSubscribers(Session session, MailInfo mailInfo) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(senderName + "@163.com"));
        msg.setSubject("【asiquantour】策略更新");

        StringBuffer mailContent = new StringBuffer();
        mailContent.append("亲爱的asiquantour用户你好！\n");
        mailContent.append("您订阅的策略" + mailInfo.strategyID + "已" + mailInfo.type.getRepre() + "！详情请查看" + mailInfo.url + "\n");
        mailContent.append("\n\nasiquantour感谢您一直以来的支持，谢谢！");
        msg.setText(mailContent.toString());

        Transport transport = session.getTransport();
        transport.connect("smtp.163.com", senderName, "qazxswedc123456");

        List<String> receiverAddresses = mailInfo.subscribers;
        Address[] wanted = new Address[receiverAddresses.size()];
        for (int i = 0; i < receiverAddresses.size(); i++) {
            wanted[i] = new InternetAddress(receiverAddresses.get(i));
        }

        transport.sendMessage(msg, wanted);
        transport.close();
        return true;
    }
}
