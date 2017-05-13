package com.edu.nju.asi.controller;

import com.sun.deploy.nativesandbox.comm.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
public class LogInController {

    @RequestMapping("/logIn")
    @ResponseBody
    public String logIn(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        System.out.println(response.getStatus());
        System.out.println("-------In  post------");
        return "1";
    }
}
