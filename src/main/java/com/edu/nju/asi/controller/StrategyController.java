package com.edu.nju.asi.controller;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.strategy.FilterConditionChinese;
import com.edu.nju.asi.infoCarrier.strategy.GeneralStrategy;
import com.edu.nju.asi.infoCarrier.strategy.RankConditionChinese;
import com.edu.nju.asi.infoCarrier.strategy.StrategyRankResult;
import com.edu.nju.asi.infoCarrier.traceBack.FilterCondition;
import com.edu.nju.asi.infoCarrier.traceBack.RankCondition;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.StrategyService;
import com.edu.nju.asi.utilities.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
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

        ModelAndView mv = new ModelAndView("generalStrategy");

        List<GeneralStrategy> generalStrategies = new LinkedList<>();

        List<Strategy> allStrategies = strategyService.getAllStrategies();
        for (Strategy nowStrategy : allStrategies) {
            if (!nowStrategy.isPrivate()) {
                generalStrategies.add(new GeneralStrategy(nowStrategy));
            }
        }

        mv.addObject("generalStrategies", generalStrategies);
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

        // 检查用户是否订阅此策略
        User curUser = (User) session.getAttribute("user");
        boolean hasSubscribe = false;
        if (wantedStrategy.getUsers() != null) {
            for (User temp : wantedStrategy.getUsers()) {
                if (temp.getUserName().equals(curUser.getUserName())) {
                    hasSubscribe = true;
                    break;
                }
            }
        }


        TraceBackCriteria criteria = JSON.parseObject(wantedStrategy.getContent(), TraceBackCriteria.class);

        String[] parts = wantedStrategy.getTraceBackInfo().split(";");
        TraceBackInfo info = JSON.parseObject(parts[0], TraceBackInfo.class);
        StrategyRankResult rankResult = JSON.parseObject(parts[1], StrategyRankResult.class);

        ModelAndView mv = new ModelAndView("searchStrategy");
        mv.addObject("nowStrategy", wantedStrategy);
        mv.addObject("hasSubscribe", hasSubscribe);
        mv.addObject("traceBackCriteria", criteria);
        mv.addObject("filterConditions", convertChinese_filter(criteria.filterConditions));
        mv.addObject("rankConditions", convertChinese_rank(criteria.rankConditions));

        /*
        回测策略的结果
         */
        // 表格数据
        mv.addObject("traceBackNums", JsonConverter.convertTraceBackNumVal(info));
        mv.addObject("absoluteReturnPeriod", JsonConverter.convertReturnPeriod(info.absoluteReturnPeriod));
        mv.addObject("relativeReturnPeriod", JsonConverter.convertReturnPeriod(info.relativeReturnPeriod));
        mv.addObject("holdingDetails", JsonConverter.convertHoldingPeriod(info.holdingDetails));
        mv.addObject("transferDayDetails", JsonConverter.convertTransferDayDetail(info.transferDayDetails));
//        mv.addObject("stageDetails", info.stageDetails);

        // 图表数据
        try {
            // json_strategyData, json_baseData
            mv.addObject("strategyCumulativeReturnChart", JsonConverter.convertTraceBack(info.strategyCumulativeReturn));
            mv.addObject("baseCumulativeReturnChart", JsonConverter.convertTraceBack(info.baseCumulativeReturn));

            // json_absoluteHistogramData, json_relativeHistogramData
            mv.addObject("absoluteReturnPeriodChart", JsonConverter.convertHistogram(info.absoluteReturnPeriod));
            mv.addObject("relativeReturnPeriodChart", JsonConverter.convertHistogram(info.relativeReturnPeriod));

            // 评分的数据画雷达图
            mv.addObject("rankResult", rankResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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

        // TODO 高源 保存时策略ID相同
        if (strategyService.isExist(newStrategy.getStrategyID())) return "-2;此策略名称已存在";

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
