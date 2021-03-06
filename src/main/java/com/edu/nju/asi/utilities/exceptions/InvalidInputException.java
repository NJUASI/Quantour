package com.edu.nju.asi.utilities.exceptions;

/**
 * @Description:输入非法抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Byron dong
 * @time:2016年12月9日 下午4:38:40
 */
public class InvalidInputException extends Exception {

	@Override
	public String getMessage(){
		return "无效输入，有不合法的输入标志符！";
	}
}