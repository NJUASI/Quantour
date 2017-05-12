package com.edu.nju.asi.utilities.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Byron Dong on 2017/4/11.
 */
public class MD5Util {

    /**
     *
     * 使用MD5加密，MD5加密后不能解密
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/11
     * @params info 需要加密的信息
     * @return String 加密后的字符
     */
    public static String encodeMD5(String info) {

        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(info.getBytes("UTF-8"));
            StringBuffer hexValue = new StringBuffer();

            for (byte md5Byte : md5Bytes) {
                int temp = ((int) md5Byte) & 0xff;

                if (temp < 16) {
                    hexValue.append(0);
                }

                hexValue.append(Integer.toHexString(temp));
            }

            return hexValue.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * 简单的加密和解密算法，一次加密，两次解密，需要商议使用问题
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/11
     * @params info 需要加密或解密的信息
     * @return String 期待的结果
     */
    private String convertMD5(String info) {

        char[] codes = info.toCharArray();

        for (char code : codes) {
            code = (char) (code ^ 'a');
        }

        return String.valueOf(codes);
    }
}
