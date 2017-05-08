package com.edu.nju.asi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cuihua on 2017/5/7.
 */
@Controller
public class TestController {


    @RequestMapping(value = "change", method = RequestMethod.POST)
    public ModelAndView firstChange(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("index");
        return view;
    }
}
