package com.edu.nju.asi.utilities.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Byron Dong on 2017/5/9.
 */
public class ApplicationContextHelper {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext(){
        if(applicationContext == null) {
            applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        }
        return applicationContext;
    }

    public static  void setApplicationContext(ApplicationContext applicationContext){
        ApplicationContextHelper.applicationContext = applicationContext;
    }
}
