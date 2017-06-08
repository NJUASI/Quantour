package com.edu.nju.asi.controller;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.traceBack.*;
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
import java.time.LocalDate;
import java.util.LinkedList;
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

        ModelAndView mv = new ModelAndView("searchStrategy");

        List<Strategy> allStrategies = strategyService.getAllStrategies();
        mv.addObject("allStrategies", allStrategies);
        return mv;
    }

    /**
     * 查看单只股票策略的详情
     *
     * @param strategyID 要查看的策略实体ID
     */
    @GetMapping("/{id}")
    public ModelAndView getOneStrategy(@PathVariable("id") String strategyID, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("index");
        }


        Strategy wantedStrategy = strategyService.getOneStrategy(strategyID);
        TraceBackCriteria criteria = JSON.parseObject(wantedStrategy.getContent(), TraceBackCriteria.class);
        TraceBackInfo info = JSON.parseObject(wantedStrategy.getTraceBackInfo(), TraceBackInfo.class);


        // 用户对此股票策略的操作（修改／删除）权限，只有创建者可以
        boolean canUpdate = false;
        User thisUser = (User) request.getSession().getAttribute("user");
        if (thisUser != null) {
            System.out.println("已登录：" + thisUser.getUserName());
            canUpdate = strategyService.canUpdate(wantedStrategy, thisUser);
        }

        ModelAndView mv = new ModelAndView("generalStrategy");
        mv.addObject("canUpdate", canUpdate);
        mv.addObject("nowStrategy", wantedStrategy);
        mv.addObject("traceBackCriteria", criteria);
        mv.addObject("filterConditions", convertChinese_filter(criteria.filterConditions));
        mv.addObject("rankConditions", convertChinese_rank(criteria.rankConditions));

        // TODO 回测结束的结果
        mv.addObject("traceBackInfo", info);


        return mv;
    }


    /**
     * 用户保存自己的股票策略
     *
     * @param newStrategy 新的策略实体
     */
    @PostMapping(value = "/save", produces = "text/html;charset=UTF-8;")
    public @ResponseBody
    String saveStrategy(@RequestParam("strategy") Strategy newStrategy, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return "-1;未登录";
        }
        System.out.println("已登录：" + thisUser.getUserName());

        newStrategy.setDate(LocalDate.now());
        boolean saveResult = strategyService.saveStrategy(newStrategy);
        if (saveResult) return "1;保存成功";
        else return "-1;保存失败";
    }

    /**
     * 创建者用户修改股票策略
     *
     * @param modifiedStrategy 被修改过的策略实体
     */
    @PostMapping("/modify")
    public @ResponseBody
    String modifyStrategy(@RequestParam("strategy") Strategy modifiedStrategy, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return "-1;未登录";
        }
        System.out.println("已登录：" + thisUser.getUserName());

        boolean modifyResult = strategyService.modify(modifiedStrategy);
        if (modifyResult) return "1;修改成功";
        else return "-1;修改失败";
    }

    /**
     * 创建者用户删除股票策略
     *
     * @param deleteStrategyID 需删除的策略实体ID
     */
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
        if (deleteResult) return "1;删除成功";
        else return "-1;删除失败";
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
        if (result) return "1;取消订阅成功";
        else return "-1;取消订阅失败";
    }


    private List<FilterConditionChinese> convertChinese_filter(List<FilterCondition> filterConditions) {
        List<FilterConditionChinese> result = new LinkedList<>();
        for (FilterCondition temp : filterConditions) {
            result.add(new FilterConditionChinese(temp));
        }
        return result;
    }

    private List<RankConditionChinese> convertChinese_rank(List<RankCondition> rankConditions) {
        List<RankConditionChinese> result = new LinkedList<>();
        for (RankCondition temp : rankConditions) {
            result.add(new RankConditionChinese(temp));
        }
        return result;
    }

}
