package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.StockComparision;
import com.edu.nju.asi.infoCarrier.StockComparsionCriteria;
import com.edu.nju.asi.service.ChartService;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.StockSituationService;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/5/13.
 */
@Controller
@RequestMapping("/stocks")
public class StockController {

    @Autowired ChartService chartService;
    @Autowired StockService stockService;
    @Autowired StockSituationService situationService;


    private LocalDate convertStringToLocalDate(String date) {
        String[] parts = date.split("-");
        return LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    /**
     * 指定日期（默认当日）股票市场查看（所有股票数据、市场温度计）
     */
    @GetMapping()
    public ModelAndView getStockMarket() {



        return null;
    }

    /**
     * 单只股票的股票详情
     */
    @GetMapping("/{id}")
    public ModelAndView getOneStock(@PathVariable("id") String stockCode) {


        return null;
    }

    /**
     * 比较两只股票
     */
    @PostMapping("/compare")
    public ModelAndView compare(HttpServletRequest request) {
        String stockCode1 = request.getParameter("stockCode1");
        String stockCode2 = request.getParameter("stockCode2");
        LocalDate start = convertStringToLocalDate(request.getParameter("start"));
        LocalDate end = convertStringToLocalDate(request.getParameter("end"));


        try {
            List<StockComparision> result = chartService.getComparision(new StockComparsionCriteria(stockCode1, stockCode2, start, end));
            // 加入Model


        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataSourceFirstDayException e) {
            e.printStackTrace();
        } catch (DateNotWithinException e) {
            e.printStackTrace();
        } catch (NoDataWithinException e) {
            e.printStackTrace();
        }

        return null;
    }
}
