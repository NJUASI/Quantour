package com.edu.nju.asi.task;


import com.edu.nju.asi.task.fileHelper.FileHelper;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class Main {

    public static void main(String[] args) {
        FileHelper fileHelper = new FileHelper("F:\\test");
        fileHelper.delete();
    }
}
