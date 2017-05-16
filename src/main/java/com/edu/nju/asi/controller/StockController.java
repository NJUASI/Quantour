package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.StockComparision;
import com.edu.nju.asi.infoCarrier.StockComparisionCriteria;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSituation;
import com.edu.nju.asi.service.ChartService;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.StockSituationService;
import com.edu.nju.asi.utilities.LocalDateHelper;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.utilities.tempHolder.StockComparisionCriteriaTempHolder;
import com.edu.nju.asi.utilities.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/5/13.
 */
@Controller
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    ChartService chartService;
    @Autowired
    StockService stockService;
    @Autowired
    StockSituationService situationService;


    /**
     * TODO 分离请求和查看
     * 指定日期（默认当日）股票市场查看（所有股票数据、市场温度计）
     */
    @GetMapping()
    public ModelAndView getStockMarket(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("stocks");

        LocalDate thisDate;
        String date = request.getParameter("date");

        // 默认获取当天
        if (date == null) thisDate = LocalDate.now();
        else thisDate = LocalDateHelper.convertString(date);

        try {
            StockSituation situation = situationService.getStockStituation(thisDate);
            mv.addObject("stockSituation", situation);

            List<Stock> stock_list = stockService.getAllStocks(thisDate);
            mv.addObject("stock_list", stock_list);
        } catch (NoSituationDataException e) {
            mv.addObject("error", e.getMessage());
        } catch (IOException e) {
            mv.addObject("error", "失败");
        }
        return mv;
    }

    /**
     * TODO 分离请求和查看
     * 单只股票的股票详情
     */
    @GetMapping("/{id}")
    public ModelAndView getOneStock(@PathVariable("id") String stockCode) {
        try {
            List<Stock> stocks = chartService.getSingleStockRecords(stockCode);
            ModelAndView modelAndView = new ModelAndView("KString");
            modelAndView.addObject("candlestickData", JsonConverter.convertCandlestick(stocks));
            modelAndView.addObject("volumeData", JsonConverter.convertVolume(stocks));
            return modelAndView;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CodeNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 显示股票比较的信息
     */
    @GetMapping("/compare")
    public ModelAndView compare(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("index");
        }

        List<StockComparision> result = (List<StockComparision>) session.getAttribute("compareResult");
        session.setAttribute("compareResult", null);

        if (result != null) {
            ModelAndView mv = new ModelAndView("stockComparision");
            mv.addObject("stockCompare1", result.get(0));
            mv.addObject("stockCompare2", result.get(1));
            return mv;
        }

        return new ModelAndView("index");
    }


    /**
     * 【请求】比较两只股票
     */
    @PostMapping(value = "/req_compare", produces = "text/html;charset=UTF-8;application/json")
    public @ResponseBody
    String reqCompare(@RequestBody StockComparisionCriteriaTempHolder holder, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        if (session == null) {
            try {
                response.sendRedirect("/welcome");
            } catch (IOException e) {
                e.printStackTrace();
                return "-1;不给看哈哈哈";
            }
            return "-1;未知错误";
        }

        StockComparisionCriteria criteria = new StockComparisionCriteria(holder);
        System.out.println(criteria.stockCode1 + "  " + criteria.stockCode2 + "  " + criteria.start + "  " + criteria.end);


        List<StockComparision> result = null;
        try {
            result = chartService.getComparision(criteria);
            session.setAttribute("compareResult", result);
            // TODO 加入当日信息显示，与界面沟通

        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        } catch (DataSourceFirstDayException | DateNotWithinException | NoDataWithinException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        }

        if (result != null) {
            System.out.println("Success");
            return "1;比较成功";
        } else return "-1;服务器开了一个小差。。请稍后重试";
    }
}
