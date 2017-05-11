package com.edu.nju.asi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuihua on 2017/5/7.
 */
@Controller
public class TestController {


    @GetMapping("/test2")
    public ModelAndView firstChange() {
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/test22")
    public String firstChange2() {
        return "test";
    }

    @RequestMapping(value = "test222")
    public String firstChange3() {
        return "test";
    }
}
