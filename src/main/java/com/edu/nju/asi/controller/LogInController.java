package com.edu.nju.asi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
public class LogInController {


    @GetMapping("/logIn")
    public boolean logIn() {
        System.out.println("-------In  get------");
        return true;
    }

    @PostMapping("/logIn")
    public boolean logIn2(HttpServletRequest request) {
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));

        System.out.println("-------In  post------");
        return true;
    }

}
