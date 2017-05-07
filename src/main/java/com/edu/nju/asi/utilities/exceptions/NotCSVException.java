package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by cuihua on 2017/4/17.
 */
public class NotCSVException extends Exception {

    @Override
    public String getMessage() {
        return "您所上传的文件不是符合条件的CSV文件，请重新上传";
    }
}
