package com.edu.nju.asi.controller;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.PrivateStockService;
import com.edu.nju.asi.service.UserService;
import com.edu.nju.asi.utilities.enums.Market;
import com.edu.nju.asi.utilities.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    PrivateStockService privateStockService;
    @Autowired
    UserService userService;

    /**
     * 普通用户注册
     */
    @PostMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        boolean result = false;
        try {
            result = userService.registerUser(new User(username, password), password2);
        } catch (DuplicatedNameException e) {
            return "-1;该用户名已被注册！";
        } catch (PasswordNotSameException e) {
            return "-1;两次密码不一致，请检查后重试！";
        } catch (IOException e) {
            return "-1;服务器开了一个小差。。请稍后重试";
        } catch (PasswordInputException e) {
            return "-1;密码需同时包含数字和字母哦！";
        } catch (InvalidInputException e) {
            return "-1;有不合法的输入标志符！";
        }

        if (result) return "1;注册成功";
        else return "-1;服务器开了一个小差。。请稍后重试";
    }


    /**
     * 检查普通用户登录是否合法 TODO 管理员未处理
     */
    @PostMapping("/log_in")
    @ResponseBody
    public String logIn(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean result = false;
        try {
            result = userService.logIn(username, password);

            HttpSession session = request.getSession(true);
            session.setAttribute("user_type", "user");
            session.setAttribute("user", new User(username, password));
        } catch (UserNotExistException e) {
            return "-1;用户尚未注册！";
        } catch (PasswordWrongException e) {
            return "-1;密码错误！";
        } catch (PasswordInputException e) {
            return "-1;密码输入格式不对！";
        } catch (InvalidInputException e) {
            return "-1;有不合法的输入标志符！";
        }

        if (result) return "1;登录成功";
        else return "-1;服务器开了一个小差。。请稍后重试";
    }


    /**
     * 用户登录初始界面，需展示用户基本信息和用户自选股列表
     */
    @GetMapping("/welcome")
    public ModelAndView welcome(@RequestParam("id") String userName, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("index");
        }

        String userType = (String) session.getAttribute("user_type");
        if (userType.equals("user")) {
            // 普通用户
            ModelAndView mv = new ModelAndView("welcome-user");

            User thisUser = (User) session.getAttribute("user");
            if (thisUser != null) {
                // TODO  暂无数据
//                try {
//                    List<Stock> psList = privateStockService.getPrivateStocks(userName, LocalDate.now());

                List<Stock> psList = new LinkedList<>();
                psList.add(new Stock("哈哈哈1", Market.SZ, 1, 1, 1, 1, "1000", 1, 1, 1));
                psList.add(new Stock("哈哈哈2", Market.SZ, 1, 1, 1, 1, "1000", 1, 1, 1));
                psList.add(new Stock("哈哈哈3", Market.SZ, 1, 1, 1, 1, "1000", 1, 1, 1));
                psList.add(new Stock("哈哈哈4", Market.SZ, 1, 1, 1, 1, "1000", 1, 1, 1));
                psList.add(new Stock("哈哈哈5", Market.SZ, 1, 1, 1, 1, "1000", 1, 1, 1));
                psList.add(new Stock("哈哈哈6", Market.SZ, 1, 1, 1, 1, "1000", 1, 1, 1));


                mv.addObject("psList", psList);
//                } catch (PrivateStockNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                return mv;
            }
        } else if (userType.equals("admin")) {
            // 管理员 TODO
            return new ModelAndView("welcome-admin");
        }

        return new ModelAndView("index");
    }

}
