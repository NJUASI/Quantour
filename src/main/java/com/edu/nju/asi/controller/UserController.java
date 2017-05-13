package com.edu.nju.asi.controller;

import com.edu.nju.asi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 修改用户信息
     */
    @PostMapping
    public ModelAndView modify() {
        return null;
    }

}
