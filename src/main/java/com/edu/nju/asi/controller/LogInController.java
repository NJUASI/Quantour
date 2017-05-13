package com.edu.nju.asi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
public class LogInController {


    @PostMapping("/logIn")
    @ResponseBody
    public String logIn2(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        System.out.println(response.getStatus());
        System.out.println("-------In  post------");
        return "1";
    }

}
