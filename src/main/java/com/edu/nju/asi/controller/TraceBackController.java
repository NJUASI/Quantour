package com.edu.nju.asi.controller;

import com.edu.nju.asi.service.TraceBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
@RequestMapping("/traceback")
public class TraceBackController {

    @Autowired
    TraceBackService traceBackService;

    /**
     * 通过选择的条件进行股票回测
     */
    @PostMapping()
    public ModelAndView traceBack(HttpServletRequest request, HttpServletResponse response){
        return null;
    }


}
