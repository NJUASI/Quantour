package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.StockComparision;
import com.edu.nju.asi.infoCarrier.StockComparisionCriteria;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSituation;
import com.edu.nju.asi.service.ChartService;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.StockSituationService;
import com.edu.nju.asi.utilities.LocalDateHelper;
import com.edu.nju.asi.utilities.StockCodeHelper;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.utilities.tempHolder.StockComparisionCriteriaTempHolder;
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
import java.util.ArrayList;
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
     * 单只股票的股票详情
     */
    @GetMapping("/{id}")
    public ModelAndView getOneStock(@PathVariable("id") String stockCode, HttpServletRequest request) {
        List<Stock> stocks = (List<Stock>) request.getSession(false).getAttribute("oneStockResult");

        if (!stockCode.equals(stocks.get(0).getStockID().getCode())) return new ModelAndView("errorPage");

        ModelAndView mv = new ModelAndView("kString");
        try {
            mv.addObject("candlestickData", JsonConverter.convertCandlestick(stocks));
            mv.addObject("volumeData", JsonConverter.convertVolume(stocks));
            return mv;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ModelAndView("index");
    }


    /**
     * 【请求】单只股票的股票详情
     */
    @PostMapping(value = "/{id}", produces = "text/html;charset=UTF-8;")
    public @ResponseBody
    String reqGetOneStock(@PathVariable("id") String stockCode, HttpServletRequest request) {
        List<Stock> stocks = null;
        try {
            stocks = chartService.getSingleStockRecords(StockCodeHelper.format(stockCode));
            HttpSession session = request.getSession(false);
            session.setAttribute("oneStockResult", stocks);

            System.out.println(stocks.size());
            System.out.println(stocks.get(0).getOpen());

        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        } catch (CodeNotFoundException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        }

        if (stocks != null) {
            System.out.println("Success");
            return "1;获取单只股票成功";
        } else return "-1;服务器开了一个小差。。请稍后重试";
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
            try {
                List<String> closes = new ArrayList<>();
                closes.add(JsonConverter.jsonOfObject(result.get(0).closes));
                closes.add(JsonConverter.jsonOfObject(result.get(1).closes));

                List<String> logarithmicYield = new ArrayList<>();
                logarithmicYield.add(JsonConverter.jsonOfObject(result.get(0).logarithmicYield));
                logarithmicYield.add(JsonConverter.jsonOfObject(result.get(1).logarithmicYield));

                List<String> name = new ArrayList<>();
                name.add(result.get(0).name);
                name.add(result.get(1).name);

                mv.addObject("closesData", JsonConverter.jsonOfObject(closes));
                mv.addObject("logarithmicYieldData", JsonConverter.jsonOfObject(logarithmicYield));
                mv.addObject("comparisionName", JsonConverter.jsonOfObject(name));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
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
