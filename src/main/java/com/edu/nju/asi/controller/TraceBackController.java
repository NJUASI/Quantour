package com.edu.nju.asi.controller;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.StrategyService;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.OptimizationCriteria;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import com.edu.nju.asi.utilities.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by cuihua on 2017/5/13.
 */
@Controller
public class TraceBackController {

    @Autowired
    TraceBackService traceBackService;


    /**
     * 通过选择的条件进行股票回测，同时显示当前用户的自选股池
     */
    @GetMapping("/trace_back")
    public ModelAndView traceBackHome(HttpServletRequest request) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return new ModelAndView("index");
        }

        System.out.println("已登录：" + thisUser.getUserName());

        ModelAndView mv = new ModelAndView("traceBack");

        String traceBackPoolStringRepre = thisUser.getTraceBackPool();
        List<String> myTraceBackPool = JSON.parseArray(traceBackPoolStringRepre, String.class);

        // 转换为界面所需的以空格隔开的字符串
        StringBuffer sb = new StringBuffer();
        if (myTraceBackPool != null) {
            for (String temp : myTraceBackPool) {
                sb.append(temp).append(" ");
            }
        }

        mv.addObject("myTraceBackPool", sb.toString());
        return mv;
    }


    /**
     * 【请求】通过选择的条件，进行股票回测，返回后JS修改页面的数据
     */
    @PostMapping(value = "/req_trace_back", produces = "text/html;charset=UTF-8;")
    public @ResponseBody
    String reqTraceBack(@RequestParam("criteriaData") TraceBackCriteria criteria, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return "-1;未登录";
        }

        System.out.println("已登录：" + thisUser.getUserName());
//        System.out.println(criteria.startDate + "  " + criteria.endDate + "  " + criteria.filterConditions.get(0).value + "  " + criteria.holdingPeriod);

        TraceBackInfo traceBackInfo = null;
        try {
            String traceBackPoolStringRepre = thisUser.getTraceBackPool();
            List<String> customizedStockPool = JSON.parseArray(traceBackPoolStringRepre, String.class);

            traceBackInfo = traceBackService.traceBack(criteria, customizedStockPool);
        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        } catch (DataSourceFirstDayException | DateNotWithinException | NoDataWithinException | UnhandleBlockTypeException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        }


        if (traceBackInfo != null) {
            try {
                System.out.println("Success");
                return "1;" + JsonConverter.convertTraceBackInfo(traceBackInfo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "-1;JSON转换失败";
            }
        } else return "-1;服务器开了一个小差。。请稍后重试";

    }


}
