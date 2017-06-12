package com.edu.nju.asi.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/4/17.
 */
public class DetectorTest {
    @Test
    public void emailDetector() throws Exception {
        Detector detector = new Detector();
        assertEquals(true,detector.emailDetector("123456@qq.com"));
        assertEquals(true,detector.emailDetector("123456@163.com"));
//        assertEquals(true,detector.emailDetector("12345dc///6@163.com"));
    }

    @Test
    public void passwordDetector() throws Exception {
        Detector detector = new Detector();
        assertEquals(true,detector.passwordDetector("123456"));
        assertEquals(true,detector.passwordDetector("qwerty"));
        assertEquals(true,detector.passwordDetector("123456qwerty"));
//        assertEquals(false,detector.passwordDetector("1234567890123456"));
//        assertEquals(false,detector.passwordDetector("qwertyuiopasdfghjkl"));
//        assertEquals(false,detector.passwordDetector("qwertyuiopa12345678990"));
//        assertEquals(false,detector.passwordDetector("567890@"));
    }

    @Test
    public void infoDetector() throws Exception {
    }

    @Test
    public void cycleDetector() throws Exception {
        Detector detector = new Detector();
        assertEquals(true,detector.cycleDetector("500","2"));
        assertEquals(true,detector.cycleDetector("11","1"));
    }

}