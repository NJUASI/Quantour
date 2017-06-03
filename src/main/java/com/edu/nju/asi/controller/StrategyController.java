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
    @GetMapping
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


    @PostMapping("/save")
    public ModelAndView saveStrategy(@RequestParam("strategy") Strategy newStrategy, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return new ModelAndView("index");
        }
        System.out.println("已登录：" + thisUser.getUserName());

        // TODO 冯俊杰 设置返回的mv视图
        ModelAndView mv = new ModelAndView();

        boolean saveResult = strategyService.saveStrategy(newStrategy);
        mv.addObject("saveResult", saveResult);
        return mv;
    }

    @PostMapping("/modify")
    public @ResponseBody
    String modifyStrategy(@RequestParam("strategy") Strategy newStrategy, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return "-1;未登录";
        }
        System.out.println("已登录：" + thisUser.getUserName());

        boolean modifyResult = strategyService.modify(newStrategy);

        // TODO 冯俊杰 设置返回JSON值
        return null;
    }

    @PostMapping("/delete")
    public @ResponseBody
    String deleteStrategy(@RequestParam("deleteStrategyID") String deleteStrategyID, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return "-1;未登录";
        }
        System.out.println("已登录：" + thisUser.getUserName());

        boolean deleteResult = strategyService.delete(thisUser, deleteStrategyID);

        // TODO 冯俊杰 设置返回JSON值
        return null;
    }

    /**
     * 查看单只股票策略的详情
     *
     * @param strategyID
     */
    @GetMapping("/{id}")
    public ModelAndView getOneStrategy(@PathVariable("id") String strategyID, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("index");
        }


        Strategy wantedStrategy = strategyService.getOneStrategy(strategyID);

        // 用户对此股票策略的操作（修改／删除）权限，只有创建者可以
        boolean canUpdate = false;
        User thisUser = (User) request.getSession().getAttribute("user");
        if (thisUser != null) {
            System.out.println("已登录：" + thisUser.getUserName());
            canUpdate = strategyService.canUpdate(wantedStrategy, thisUser);
        }

        // TODO 用此策略进行回测，得其一些指标并画图



        // TODO 冯俊杰 设置返回的mv视图
        ModelAndView mv = new ModelAndView();
        return mv;
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
