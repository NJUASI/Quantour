package com.edu.nju.asi.utilities.exceptions;

/**
 * @Description:输入非法抛出该异常
 * @author:Byron dong
 * @lastChangedBy:Byron dong
 * @time:2016年12月10日
 */
public class PasswordInputException extends Exception{

	@Override
	public String getMessage(){
		return "密码需同时包含数字和字母";
	}

}
