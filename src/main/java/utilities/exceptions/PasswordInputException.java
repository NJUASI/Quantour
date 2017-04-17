package utilities.exceptions;

/**
 * @Description:输入非法抛出该异常
 * @author:Byron dong
 * @lastChangedBy:Byron dong
 * @time:2016年12月10日
 */
public class PasswordInputException extends Exception{

	@Override
	public String getMessage(){
		return "密码至少要含有一个数字和一个字母";
	}

}
