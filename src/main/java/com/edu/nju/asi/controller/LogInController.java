package com.edu.nju.asi.controller;

import com.edu.nju.asi.service.UserService;
import com.edu.nju.asi.utilities.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
@RequestMapping("/login")
public class LogInController {

    @Autowired
    UserService userService;


    /**
     * 用户注册
     */
    @PostMapping()
    public String signUp() {
        return null;
    }

    /**
     * 用户登录
     */
    @GetMapping
    public String signIn() {
        return null;
    }

    /**
     * 用户登出
     */
    @GetMapping
    public String logOut() {
        return null;
    }
}
