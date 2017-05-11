package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.UserDao;
import com.edu.nju.asi.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Byron Dong on 2017/3/6.
 */
public class UserDaoImplTest {
    UserDao userDao;
    User user;
    @Before
    public void before() throws Exception {
        userDao = new UserDaoImpl();
        user = new User();
        user.setUserName("gong");
        user.setPassword("123456");
    }

    @Test
    public void add() throws Exception {
        if (userDao.get("gong")!=null){
            userDao.add(user);
            User po1 = userDao.get("gong");
            Assert.assertEquals("123456",po1.getPassword());
        }
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals("123456", user.getPassword());
    }

    @Test
    public void modify() throws Exception {
        if(userDao.get("gong")!=null){
            user.setPassword("123456789");
            userDao.modify(user);
            User po1 = userDao.get("gong");
            Assert.assertEquals("123456789",po1.getPassword());
        }
    }

}