package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.model.TraceBackStockPool;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.TraceBackStockPoolService;
import com.edu.nju.asi.utilities.Detector;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.utilities.tempHolder.TraceBackCriteriaTempHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    TraceBackStockPoolService stockPoolService;

    /**
     * 通过选择的条件进行股票回测
     */
    @GetMapping(value = "/traceback_home", produces = "text/html;charset=UTF-8;application/json")
    public String traceBack(HttpServletRequest request, HttpServletResponse response) {
        return "traceback_home";
    }


    /**
     * 通过选择的条件进行股票回测
     */
    @PostMapping(value = "/traceback", produces = "text/html;charset=UTF-8;application/json")
    public ModelAndView traceBack(@RequestBody TraceBackCriteriaTempHolder criteriaTempHolder, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("index");

        // 限制进入
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("未登录");

            try {
                response.sendRedirect("/welcome");
            } catch (IOException e) {
                e.printStackTrace();
                return mv;
            }
            return mv;
        }

        TraceBackCriteria criteria = new TraceBackCriteria(criteriaTempHolder);

        // TODO 冯俊杰：检测是否合法，可尝试分离
//        try {
//            boolean detect1 = new Detector().cycleDetector(String.valueOf(criteria.holdingPeriod), "2");
//        } catch (InvalidInputException e) {
//            e.printStackTrace();
//        }

        mv.setViewName("traceback");

        User thisUser = (User) request.getSession().getAttribute("user");
        List<String> stockPool = stockPoolService.getTraceBackStockPoolCodes(thisUser.getUserName());

        System.out.println("已登录：" + thisUser.getUserName());


        try {
            TraceBackInfo traceBackInfo = traceBackService.traceBack(criteria, stockPool);
            mv.addObject("result", traceBackInfo);
        } catch (IOException e) {
            mv.addObject("error", "失败");
        } catch (DataSourceFirstDayException e) {
            mv.addObject("error", "所选开始时间为数据源中第一日，不能比其更前进行回测啦！");
        } catch (DateNotWithinException e) {
            mv.addObject("error", "时间太广，暂缺数据。。");
        } catch (NoDataWithinException e) {
            mv.addObject("error", "所选时间区间内没有数据");
        } catch (UnhandleBlockTypeException e) {
            mv.addObject("error", "数据源中有未处理的板块信息");
        }

        return mv;
    }


}
