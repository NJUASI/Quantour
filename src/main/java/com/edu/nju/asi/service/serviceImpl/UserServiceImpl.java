package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.UserDao;
import com.edu.nju.asi.dao.daoImpl.UserDaoImpl;
import com.edu.nju.asi.utilities.AttahmentsInitializer;
import com.edu.nju.asi.utilities.Detector;
import com.edu.nju.asi.utilities.util.MD5Util;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.po.UserPO;
import com.edu.nju.asi.service.UserService;
import com.edu.nju.asi.vo.UserVO;

import java.io.IOException;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/12
 *
 * 去除在用户注册时为用户新建一个properties的实现
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl() {
         userDao = new UserDaoImpl();
    }

    /**
     * 用户注册.
     *
     * @auther Harvey
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/15
     * @param userVO 注册用户信息
     * @param password2
     * @return 是否注册成功
     * @throws DuplicatedNameException 用户名重复
     */
    @Override
    public boolean registerUser(UserVO userVO, String password2) throws DuplicatedNameException, PasswordNotSameException, IOException, PasswordInputException, InvalidInputException {

        AttahmentsInitializer.init();

        if(userDao.getAllUserNames().contains(userVO.userName)){
            throw new DuplicatedNameException();
        }
        else if(!userVO.password.equals(password2)){
            throw new PasswordNotSameException();
        }

        //检测密码与用户名是否存在不合法符号
        Detector detector = new Detector();
        detector.passwordDetector(userVO.password);
        detector.infoDetector(userVO.userName);

        userVO.password = MD5Util.encodeMD5(userVO.password);
//        userDao.add(new UserPO(userVO));
        return true;
    }

    /**
     * 修改用户信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userVO 修改后的用户信息
     * @return 是否修改成功
     */
    @Override
    public boolean modifyUser(UserVO userVO) throws PasswordInputException {

        //检测密码与用户名是否存在不合法符号
        Detector detector = new Detector();
        detector.passwordDetector(userVO.password);

        userVO.password = MD5Util.encodeMD5(userVO.password);
//        return userDao.modify(new UserPO(userVO));
        return true;
    }

    /**
     * 用户登录
     *
     * @param userName 用户名称
     * @param password
     * @return 是否登录成功
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean logIn(String userName, String password) throws UserNotExistException, DuplicateLoginException, PasswordWrongException, PasswordInputException, InvalidInputException {

        //检测密码与用户名是否存在不合法符号
        Detector detector = new Detector();
        detector.passwordDetector(password);
        detector.infoDetector(userName);

        if(!userDao.getAllUserNames().contains(userName)){
            throw new UserNotExistException();
        }
        else{
            if (!userDao.get(userName).getPassword().equals(MD5Util.encodeMD5(password))){
                throw new PasswordWrongException();
            }
            else{
                return true;
            }
        }
    }
}
