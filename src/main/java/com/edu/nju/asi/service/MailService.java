package com.edu.nju.asi.service;

import com.edu.nju.asi.infoCarrier.MailInfo;

import javax.mail.MessagingException;

/**
 * Created by cuihua on 2017/6/3.
 */
public interface MailService {

    boolean notify(MailInfo mailInfo) throws MessagingException;
}
