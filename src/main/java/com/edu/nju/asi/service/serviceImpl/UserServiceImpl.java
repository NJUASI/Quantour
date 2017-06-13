package com.edu.nju.asi.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.UserDao;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.UserService;
import com.edu.nju.asi.utilities.Detector;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.utilities.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/12
 * <p>
 * 去除在用户注册时为用户新建一个properties的实现
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    StockDao stockDao;

    public UserServiceImpl() {
//        userDao = new UserDaoImpl();
    }

    /**
     * 用户注册.
     *
     * @param user      注册用户信息
     * @param password2
     * @return 是否注册成功
     * @throws DuplicatedNameException 用户名重复
     * @auther Harvey
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/15
     */
    @Override
    public boolean registerUser(User user, String password2) throws DuplicatedNameException, PasswordNotSameException, IOException, PasswordInputException, InvalidInputException {
        if (userDao.getAllUserNames().contains(user.getUserName())) {
            throw new DuplicatedNameException();
        } else if (!user.getPassword().equals(password2)) {
            throw new PasswordNotSameException();
        }

        //检测密码与用户名是否存在不合法符号
        Detector detector = new Detector();
        detector.passwordDetector(user.getPassword());
        detector.infoDetector(user.getUserName());

        user.setPassword(MD5Util.encodeMD5(user.getPassword()));
        userDao.add(user);
        return true;
    }

    /**
     * 修改用户信息
     *
     * @param user 修改后的用户信息
     * @return 是否修改成功
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean modifyUser(User user) throws PasswordInputException {

        //检测密码与用户名是否存在不合法符号
        Detector detector = new Detector();
        detector.passwordDetector(user.getPassword());

        user.setPassword(MD5Util.encodeMD5(user.getPassword()));
        return userDao.modify(user);
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
    public boolean logIn(String userName, String password) throws UserNotExistException, PasswordWrongException, PasswordInputException, InvalidInputException {

        //检测密码与用户名是否存在不合法符号
        Detector detector = new Detector();
        detector.passwordDetector(password);
        detector.infoDetector(userName);

        if (!userDao.getAllUserNames().contains(userName)) {
            throw new UserNotExistException();
        } else {
            if (!userDao.get(userName).getPassword().equals(MD5Util.encodeMD5(password))) {
                throw new PasswordWrongException();
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean modifyMyTraceBackPool(List<String> modifiedTraceBackPool, User curUser) throws TraceBackStockExistedException {
        Set<String> allStockCodes = stockDao.getAllStocksCode().keySet();

        List<String> notExistCodes = new LinkedList<>();

        for (String nowStockCode : modifiedTraceBackPool) {
            boolean isCorrect = false;
            for (String temp : allStockCodes) {
                if (nowStockCode.equals(temp)) {
                    isCorrect = true;
                    break;
                }
            }

            if (!isCorrect) notExistCodes.add(nowStockCode);
        }

        if (notExistCodes.size() != 0) throw new TraceBackStockExistedException(notExistCodes);
        else {
            curUser.setTraceBackPool(JSON.toJSONString(modifiedTraceBackPool));
            return userDao.modify(curUser);
        }
    }
}
