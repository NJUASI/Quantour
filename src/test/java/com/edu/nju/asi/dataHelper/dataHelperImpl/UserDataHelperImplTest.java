package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.UserDataHelper;
import com.edu.nju.asi.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/11.
 */
public class UserDataHelperImplTest {

    private UserDataHelper userDataHelper;

    @Before
    public void setUp() throws Exception {
        userDataHelper = HelperManager.userDataHelper;
    }

    @Test
    public void add() throws Exception {
        User user1 = new User();
        user1.setUserName("ByronDong");
        user1.setPassword("123456");

        User user2 = new User();
        user2.setUserName("Harvey Gong");
        user2.setPassword("789012");

        User user3 = new User();
        user3.setUserName("61990");
        user3.setPassword("345678");

        User user4 = new User();
        user4.setUserName("CharlesFeng47");
        user4.setPassword("901234");

        userDataHelper.add(user1);
        userDataHelper.add(user2);
        userDataHelper.add(user3);
        userDataHelper.add(user4);
    }

    @Test
    public void get() throws Exception {
        assertEquals("123456",userDataHelper.get("ByronDong").getPassword());
        assertEquals("789012",userDataHelper.get("Harvey Gong").getPassword());
        assertEquals("345678",userDataHelper.get("61990").getPassword());
        assertEquals("qwertyuiop",userDataHelper.get("CharlesFeng47").getPassword());
    }

    @Test
    public void update() throws Exception {
        User user4 = new User();
        user4.setUserName("CharlesFeng47");
        user4.setPassword("qwertyuiop");
        userDataHelper.update(user4);
    }

    @Test
    public void getAllUserNames() throws Exception {
        List<String> list = userDataHelper.getAllUserNames();
        assertEquals("ByronDong",list.get(1));
        assertEquals("Harvey Gong",list.get(3));
        assertEquals("61990",list.get(0));
        assertEquals("CharlesFeng47",list.get(2));
    }

}