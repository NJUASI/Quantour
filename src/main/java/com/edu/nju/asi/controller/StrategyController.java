package com.edu.nju.asi.controller;

import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by cuihua on 2017/6/2.
 * <p>
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
    public ModelAndView getAll(HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        if (session == null) {
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // TODO 冯俊杰 设置返回的mv视图
        ModelAndView mv = new ModelAndView();

        List<Strategy> allStrategies = strategyService.getAllStrategies();
        mv.addObject("allStrategies", allStrategies);
        return mv;
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
     *
     * @param strategyID 要订阅的策略ID
     */
    @PostMapping("/{id}/subscribe")
    public @ResponseBody
    String subscribe(@PathVariable("id") String strategyID, HttpServletRequest request) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return "-1;未登录";
        }
        System.out.println("已登录：" + thisUser.getUserName());

        boolean result = strategyService.subscribe(strategyID, thisUser);
        if (result) return "1;订阅成功";
        else return "-1;订阅失败";
    }

    /**
     * 用户取消订阅股票策略
     *
     * @param strategyID 要取消订阅的策略ID
     */
    @PostMapping("/{id}/revoke_subscribe")
    public @ResponseBody
    String revokeSubscribe(@PathVariable("id") String strategyID, HttpServletRequest request) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return "-1;未登录";
        }
        System.out.println("已登录：" + thisUser.getUserName());

        boolean result = strategyService.revokeSubscribe(strategyID, thisUser);
        if (result) return "1;订阅成功";
        else return "-1;订阅失败";
    }


}
