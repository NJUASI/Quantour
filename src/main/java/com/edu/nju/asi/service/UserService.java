package com.edu.nju.asi.service;

import com.edu.nju.asi.model.User;
import com.edu.nju.asi.utilities.exceptions.*;

import java.io.IOException;

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
     * @param user 注册用户信息
     * @param password2
     * @return 是否注册成功
     * @throws DuplicatedNameException 用户名重复
     */
    boolean registerUser(User user, String password2) throws DuplicatedNameException, PasswordNotSameException, IOException, PasswordInputException, InvalidInputException;

    /**
     * 修改用户信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param user 修改后的用户信息
     * @return 是否修改成功
     */
    boolean modifyUser(User user) throws PasswordInputException;

    /**
     * 用户登录
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @return 是否登录成功
     */
    boolean logIn(String userName, String password) throws UserNotExistException, PasswordWrongException, PasswordInputException, InvalidInputException;

}
