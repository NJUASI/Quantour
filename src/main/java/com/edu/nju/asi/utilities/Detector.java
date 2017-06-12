package com.edu.nju.asi.utilities;

import com.edu.nju.asi.utilities.exceptions.EmailInputException;
import com.edu.nju.asi.utilities.exceptions.InvalidInputException;
import com.edu.nju.asi.utilities.exceptions.PasswordInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Byron Dong lastChangedBy Byron Dong updateTime 2017/4/16
 *
 */
public class Detector {

	private String expression; //存放正则表达式

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/4/16
	 * @param express
	 *            传入需要检查不合法符号的密码
	 * @return boolean 是否符合要求规范
	 */
	public boolean passwordDetector(String express) throws PasswordInputException {
		
		expression = "^[[a-zA-Z]|\\d]{6,15}$";
		
		if(!this.getResultOfDetector(express)){
			throw new PasswordInputException(); //密码必须是数字与字母的组合
		}
		return true;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/6/12
	 * @param express
	 *            传入需要检查不合法符号的邮件
	 * @return boolean 是否符合要求规范
	 */
	public boolean emailDetector(String express) throws EmailInputException {

		expression =  "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

		if(!this.getResultOfDetector(express)){
			throw new EmailInputException(); //密码必须是数字与字母的组合
		}
		return true;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/4/16
	 * @param express
	 *            传入需要检查不合法符号的信息内容
	 * @return boolean 是否符合要求规范
	 */
	public boolean infoDetector(String express) throws InvalidInputException {
		expression = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
		
		if(!this.getResultOfDetector(express)){
			throw new InvalidInputException(); 	//统一检测填写信息内容中的不合法标识符
		}
		return true;
	}

    /**
     * @author Byron Dong
     * @lastChangedBy Byron Dong
     * @updateTime 2016/4/16
     * @param express
     *            传入需要检查不合法符号的信息内容，必须输入小于length长度的数字
     * @return boolean 是否符合要求规范
     */
    public boolean cycleDetector(String express, String length) throws InvalidInputException {
        expression = "[1-9]\\d{0,"+length+"}+";

        if(!this.getResultOfDetector(express)){
            throw new InvalidInputException(); 	//统一检测填写信息内容中的不合法标识符
        }
        return true;
    }
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2017/4/16
	 * @param express
	 *            传入需要检查不合法符号的表达式
	 * @return boolean 是否符合要求规范
	 */
	private boolean getResultOfDetector(String express){
		
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(express);
		boolean result = matcher.matches(); 
		
		return result; //得到匹配正则表达式的结果
	}
	
}
