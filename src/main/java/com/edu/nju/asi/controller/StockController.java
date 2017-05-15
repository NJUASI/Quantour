package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.StockComparision;
import com.edu.nju.asi.infoCarrier.StockComparsionCriteria;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSituation;
import com.edu.nju.asi.service.ChartService;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.StockSituationService;
import com.edu.nju.asi.utilities.LocalDateHelper;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.utilities.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView getOneStock(@PathVariable("id") String stockCode) {
        try {
            List<Stock> stocks = chartService.getSingleStockRecords(stockCode);
            ModelAndView modelAndView = new ModelAndView("stocks");
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
     * 比较两只股票
     */
    @PostMapping("/compare")
    public ModelAndView compare(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("index");

        // 限制进入
        HttpSession session = request.getSession(false);
        if (session == null) {
            try {
                response.sendRedirect("/welcome");
            } catch (IOException e) {
                e.printStackTrace();
                return mv;
            }
            return mv;
        } else {
            mv.setViewName("stockComparision");

            String stockCode1 = request.getParameter("stockCode1");
            String stockCode2 = request.getParameter("stockCode2");
            LocalDate start = LocalDateHelper.convertString(request.getParameter("start"));
            LocalDate end = LocalDateHelper.convertString(request.getParameter("end"));


            try {
                List<StockComparision> result = chartService.getComparision(new StockComparsionCriteria(stockCode1, stockCode2, start, end));
                mv.addObject("compare1", result.get(0));
                mv.addObject("compare2", result.get(1));

                // TODO 加入当日信息显示


                return mv;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DataSourceFirstDayException e) {
                e.printStackTrace();
            } catch (DateNotWithinException e) {
                e.printStackTrace();
            } catch (NoDataWithinException e) {
                e.printStackTrace();
            }
        }
        return mv;
    }
}
