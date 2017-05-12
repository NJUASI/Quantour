package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.edu.nju.asi.po.UserPO;

/**
 * Created by Byron Dong on 2017/3/6.
 */
public class UserDaoImplTest {
    UserDao userDao;
    UserPO po;
    @Before
    public void before() throws Exception {
        userDao = new UserDaoImpl();
        po = new UserPO();
        po.setUserName("gong");
        po.setPassword("123456");
    }

    @Test
    public void add() throws Exception {
        if (userDao.get("gong")!=null){
            userDao.add(po);
            UserPO po1 = userDao.get("gong");
            Assert.assertEquals("123456",po1.getPassword());
        }
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals("123456",po.getPassword());
    }

    @Test
    public void modify() throws Exception {
        if(userDao.get("gong")!=null){
            po.setPassword("123456789");
            userDao.modify(po);
            UserPO po1 = userDao.get("gong");
            Assert.assertEquals("123456789",po1.getPassword());
        }
    }

}