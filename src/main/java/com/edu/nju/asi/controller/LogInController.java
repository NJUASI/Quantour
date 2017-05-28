package com.edu.nju.asi.controller;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.PrivateStockService;
import com.edu.nju.asi.service.UserService;
import com.edu.nju.asi.utilities.enums.Market;
import com.edu.nju.asi.utilities.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
public class LogInController {

    @Autowired
    UserService userService;

    @GetMapping
    public String home(){
        return "index";
    }


    /**
     * 【请求】普通用户注册
     */
    @PostMapping(value = "/req_register", produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String reqRegister(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        System.out.println(username + "  " + password + "  " + password2);

        boolean result = false;
        try {
            User thisUser = new User(username, password);
            result = userService.registerUser(thisUser, password2);

            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "user");
            session.setAttribute("user", thisUser);
        } catch (IOException e) {
            return "-1;服务器开了一个小差。。请稍后重试";
        } catch (DuplicatedNameException | PasswordNotSameException | PasswordInputException | InvalidInputException e) {
            return "-1;" + e.getMessage();
        }

        if (result) return "1;注册成功";
        else return "-1;服务器开了一个小差。。请稍后重试";
    }


    /**
     * 【请求】用户登录 TODO 管理员未处理
     * produces少一点都不行
     */
    @PostMapping(value = "/req_log_in", produces = "text/html;charset=UTF-8;application/json")
    public @ResponseBody
    String reqLogIn(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        if (user != null) {
            System.out.println(user.getUserName() + "  " + user.getPassword());
        } else {
            System.out.println("--------------FAIL!! The user is NULL!!-------------");
        }

        boolean result = false;
        try {
            result = userService.logIn(user.getUserName(), user.getPassword());

            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "user");
            session.setAttribute("user", user);
        } catch (UserNotExistException | PasswordWrongException | PasswordInputException | InvalidInputException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        }

        if (result) {
            System.out.println("Success");
            return "1;登录成功";
        } else return "-1;服务器开了一个小差。。请稍后重试";
    }

}
