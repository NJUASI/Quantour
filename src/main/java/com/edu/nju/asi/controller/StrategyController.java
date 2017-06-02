package com.edu.nju.asi.controller;

import com.edu.nju.asi.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuihua on 2017/6/2.
 *
 * 用户对被人创建的共享的股票策略
 */
@Controller
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    StrategyService strategyService;


    /**
     * 查看所有共享的股票策略
     */
    public ModelAndView getAll() {


        return null;
    }


    /**
     * 查看单只股票策略的详情
     *
     * @param strategyID
     */
    @GetMapping("/{id}")
    public ModelAndView getOneStrategy(@PathVariable("id") String strategyID) {


        return null;
    }

    /**
     * 用户订阅股票策略
     * @param strategyID 要订阅的策略ID
     */
    @PostMapping("/{id}/subscribe")
    public @ResponseBody
    String subscribe(@PathVariable("id") String strategyID) {

        return null;
    }

    /**
     * 用户取消订阅股票策略
     * @param strategyID 要取消订阅的策略ID
     */
    @PostMapping("/{id}/revoke_subscribe")
    public @ResponseBody
    String revokeSubscribe(@PathVariable("id") String strategyID) {

        return null;
    }



}
