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

    @GetMapping
    public String home(){
        return "index";
    }


    /**
     * 普通用户登录初始界面，需展示用户基本信息和用户自选股列表
     */
    @GetMapping("/welcome")
    public ModelAndView welcome(@RequestParam("id") String userName, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("index");
        }

        String userType = (String) session.getAttribute("userType");
        if (userType.equals("user")) {
            // 普通用户
            ModelAndView mv = new ModelAndView("welcome_user");
            List<Stock> psList = (List<Stock>) session.getAttribute("privateStockList");
            mv.addObject("ps_list", psList);
            return mv;
        } else if (userType.equals("admin")) {
            // 管理员 TODO
            return new ModelAndView("welcome_admin");
        }

        return new ModelAndView("index");
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
            session.setAttribute("privateStockList", null);
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
            // TODO 暂无数据
//            List<Stock> psList = privateStockService.getPrivateStocks(user.getUserName(), LocalDate.now());

            List<Stock> psList = new LinkedList<>();
            psList.add(new Stock("哈哈哈1", Market.SZ, 1, 1, 1, 1, "100","1000", 1, 1, 1,1,"2", "2"));
            psList.add(new Stock("哈哈哈2", Market.SZ, 1, 1, 1, 1, "100","1000", 1, 1, 1,1,"2", "2"));
            psList.add(new Stock("哈哈哈3", Market.SZ, 1, 1, 1, 1, "100","1000", 1, 1, 1,1,"2", "2"));

            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "user");
            session.setAttribute("user", user);
            session.setAttribute("privateStockList", psList);
        } catch (UserNotExistException | PasswordWrongException | PasswordInputException | InvalidInputException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "-1;IO读取失败！";
        }

        if (result) {
            System.out.println("Success");
            return "1;登录成功";
        } else return "-1;服务器开了一个小差。。请稍后重试";
    }

}
