package com.edu.nju.asi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public boolean logIn2() {
        System.out.println("-------In  post------");
        return true;
    }

}
