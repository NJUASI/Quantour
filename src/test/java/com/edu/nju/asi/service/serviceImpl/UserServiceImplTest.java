package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * UserServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 13, 2017</pre>
 */
public class UserServiceImplTest {

    UserService userService;


    @Before
    public void before() throws Exception {
        userService = new UserServiceImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: registerUser(User user, String password2)
     */
    @Test
    public void testRegisterUser() throws Exception {
        boolean result = userService.registerUser(new User("Charles", "qwertyuiop123456"), "qwertyuiop123456");
        Assert.assertEquals(true, result);
    }

    /**
     * Method: modifyUser(User user)
     */
    @Test
    public void testModifyUser() throws Exception {
    }

    /**
     * Method: logIn(String userName, String password)
     */
    @Test
    public void testLogIn() throws Exception {
    }


} 
