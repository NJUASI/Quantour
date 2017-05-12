package com.edu.nju.asi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
public class LogInController {


    @PostMapping("/logIn")
    public boolean logIn() {
        return true;
    }

}
