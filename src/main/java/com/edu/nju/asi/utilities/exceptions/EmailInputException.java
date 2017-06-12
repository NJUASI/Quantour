package com.edu.nju.asi.utilities.exceptions;

/**
 * @Description:输入非法抛出该异常
 * @author:Byron dong
 * @lastChangedBy:Byron dong
 * @time:2016年12月10日
 */
public class EmailInputException extends Exception{

	@Override
	public String getMessage(){
		return "邮箱格式不符合规范";
	}

}
