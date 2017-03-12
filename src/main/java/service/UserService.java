package service;

import utilities.exceptions.*;
import vo.UserVO;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 */
public interface UserService{

    /**
     * 用户注册.
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userVO 注册用户信息
     * @param password2
     * @return 是否注册成功
     * @throws DuplicatedNameException 用户名重复
     */
    public boolean registerUser(UserVO userVO, String password2) throws DuplicatedNameException, PasswordNotSameException;

    /**
     * 修改用户信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userVO 修改后的用户信息
     * @return 是否修改成功
     */
    public boolean modifyUser(UserVO userVO);

    /**
     * 查看用户信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param  userName 用户姓名
     * @return 用户信息
     */
    public UserVO checkUserInfo(String userName);

    /**
     * 用户登录
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @return 是否登录成功
     */
    boolean logIn(String userName,String password) throws DuplicateLoginException, UserNotExistException, PasswordWrongException;

    /**
     * 用户登出
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户姓名
     * @return 是否注销成功
     */
    public boolean logOut(String userName);
}
