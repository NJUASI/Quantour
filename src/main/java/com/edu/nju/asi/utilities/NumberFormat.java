package com.edu.nju.asi.utilities;

import java.text.DecimalFormat;

/**
 * Created by Byron Dong on 2017/4/18.
 */
public class NumberFormat {

    public static String percentFormat(double num,int length){
        String pattern = "#0.";

        for(int i=0;i<length;i++){
            pattern = pattern+"0";
        }
        pattern = pattern+"%";
        DecimalFormat df=new DecimalFormat(pattern);

        return df.format(num);
    }

    public static String decimaFormat(double num,int length){
        String pattern = "#0.";

        for(int i=0;i<length;i++){
            pattern = pattern+"0";
        }
        DecimalFormat df=new DecimalFormat(pattern);

        return df.format(num);
    }
}
