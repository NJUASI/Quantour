package com.edu.nju.asi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edu.nju.asi.infoCarrier.ChartShowCriteria;
import com.edu.nju.asi.infoCarrier.StockComparision;
import com.edu.nju.asi.infoCarrier.StockComparisionCriteria;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSituation;
import com.edu.nju.asi.service.ChartService;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.StockSituationService;
import com.edu.nju.asi.utilities.LocalDateHelper;
import com.edu.nju.asi.utilities.NumberFormat;
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
import java.util.LinkedList;
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


    // 测试用
    private final static LocalDate nowDate = LocalDate.of(2014, 2, 21);
//    private final static LocalDate nowDate = LocalDate.now();


    /**
     * 指定日期（默认当日）股票市场查看（所有股票数据、市场温度计）
     */
    @GetMapping()
    public ModelAndView getStockMarket(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("stocks");

        HttpSession session = request.getSession(false);
        if (session.getAttribute("oneDateStockList") == null) {
            System.out.println("默认进来的");

            // 刚进来默认访问此方法，默认显示当日，先调用请求方法
            String reqResult = reqGetStockMarket(request, response);
            if (reqResult.split(";")[0].equals("1")) {
                System.out.println("请求成功");

                StockSituation situation = (StockSituation) session.getAttribute("oneDateSituation");
                List<Stock> stocks = (List<Stock>) session.getAttribute("oneDateStockList");
                session.setAttribute("oneDateSituation", null);
                session.setAttribute("oneDateStockList", null);

                mv.addObject("situation", situation);
                mv.addObject("stockList", stocks);

                System.out.println(stocks.size() + "\n\n\n");
            } else {
                System.out.println("请求失败");
                return new ModelAndView("errorPage");
            }


        } else {
            // 是通过js请求来的，只需要add进mv
            System.out.println("通过js访问的");

            StockSituation situation = (StockSituation) session.getAttribute("oneDateSituation");
            List<Stock> stocks = (List<Stock>) session.getAttribute("oneDateStockList");
            session.setAttribute("oneDateSituation", null);
            session.setAttribute("oneDateStockList", null);

            mv.addObject("situation", situation);
            mv.addObject("stockList", stocks);

            System.out.println(stocks.size() + "\n\n\n");

        }
        return mv;
    }


    /**
     * 【请求】指定日期（默认当日）股票市场查看（所有股票数据、市场温度计）
     */
    @PostMapping(produces = "text/html;charset=UTF-8;")
    public @ResponseBody
    String reqGetStockMarket(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("--------在req中-----------");
        LocalDate thisDate;
        String date = request.getParameter("date");

        // 默认获取当天
        if (date == null) {
            System.out.println("js没有。。");
            thisDate = nowDate;
        } else {
            System.out.println("JS有的！！");
            thisDate = LocalDateHelper.convertString(date);
        }

        StockSituation situation = null;
        List<Stock> stockList = null;
        try {
            situation = situationService.getStockStituation(thisDate);
            stockList = stockService.getAllStocks(thisDate);

            HttpSession session = request.getSession(false);
            session.setAttribute("oneDateSituation", situation);
            session.setAttribute("oneDateStockList", stockList);

            System.out.println(stockList.size());
        } catch (NoSituationDataException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        }

        if (stockList != null) {
            System.out.println("Success");
            return "1;获取单只股票成功";
        } else return "-1;服务器开了一个小差。。请稍后重试";
    }

    /**
     * 单只股票的股票详情
     */
    @GetMapping("/{id}")
    public ModelAndView getOneStock(@PathVariable("id") String stockCode, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<Stock> stocks = (List<Stock>) session.getAttribute("oneStockResult");
        session.setAttribute("oneStockResult", null);

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
        LocalDate startDate, endDate;
        String startDateString = request.getParameter("startDate");
        String endDateString = request.getParameter("endDate");

        // 默认显示一年内的K线图
        if (startDateString == null) {
            System.out.println("默认从行情界面进入，显示最新的。。");
            startDate = nowDate.minusYears(1);
            endDate = nowDate;
        } else {
            System.out.println("在个股界面选择了日期！！");
            startDate = LocalDateHelper.convertString(startDateString);
            endDate = LocalDateHelper.convertString(endDateString);
        }


        List<Stock> stocks = null;
        try {
            stocks = chartService.getSingleStockRecords(new ChartShowCriteria(StockCodeHelper.format(stockCode), startDate, endDate));
            HttpSession session = request.getSession(false);
            session.setAttribute("oneStockResult", stocks);

            System.out.println(stocks.size());
            System.out.println(stocks.get(0).getOpen());

        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        } catch (CodeNotFoundException | NoDataWithinException | DateNotWithinException e) {
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
            System.out.println("Session为空");
            return new ModelAndView("index");
        }

        List<StockComparision> result = (List<StockComparision>) session.getAttribute("compareResult");
        session.setAttribute("compareResult", null);

        if (result == null) {
            // 默认进来的
            return new ModelAndView("stockComparision");
        } else {
            // 通过js进来的
            ModelAndView mv = new ModelAndView("stockComparision");
            try {
                // 数值型对比信息
                mv.addObject("stockCompareNum1", convertcomparisionNumVal(result.get(0)));
                mv.addObject("stockCompareNum2", convertcomparisionNumVal(result.get(1)));


                // 图表型对比信息
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
    }


    /**
     * 【请求】比较两只股票
     */
    @PostMapping(value = "/req_compare", produces = "text/html;charset=UTF-8;application/json")
    public @ResponseBody
    String reqCompare(@RequestBody StockComparisionCriteriaTempHolder holder, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // 限制进入
        HttpSession session = request.getSession(false);
        if (session == null) {
            try {
                response.sendRedirect("/welcome");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }

        StockComparisionCriteria criteria = new StockComparisionCriteria(holder);
        System.out.println(criteria.stockCode1 + "  " + criteria.stockCode2 + "  " + criteria.start + "  " + criteria.end);

        List<StockComparision> result = null;

//        List<List<String>> results = new ArrayList<>();
        List<List<String>> closes01, closes02;
        try {
            result = chartService.getComparision(criteria);
//            session.setAttribute("compareResult", result);
            // TODO 加入当日信息显示，与界面沟通

//            List<String> list2= new ArrayList<>();
            closes01 = (JsonConverter.convertComparision(result.get(0).closes));
            closes02 = (JsonConverter.convertComparision(result.get(1).closes));

            String jsonString1 = JSONArray.toJSONString(closes01);
            String jsonString2 = JSONArray.toJSONString(closes02);


            System.out.println("Success");
            System.out.println(closes01);
            System.out.println(closes02);

            return jsonString1 + ";" + jsonString2;

//            list2.add(JsonConverter.convertComparision(result.get(0).logarithmicYield));
//            list2.add(JsonConverter.convertComparision(result.get(1).logarithmicYield));
//            results.add(list1);
//            results.add(list2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (DataSourceFirstDayException | DateNotWithinException | NoDataWithinException e) {
            e.printStackTrace();
            return null;
        }
    }


    private List<String> convertcomparisionNumVal(StockComparision comparision) {
        List<String> result = new LinkedList<>();
        result.add(comparision.name);
        result.add(NumberFormat.decimaFormat(comparision.max, 4));
        result.add(NumberFormat.decimaFormat(comparision.min, 4));
        result.add(NumberFormat.percentFormat(comparision.increaseMargin, 2));
        result.add(NumberFormat.decimaFormat(comparision.logarithmicYieldVariance, 4));

        for (String tempStr : result) {
            System.out.println(tempStr);
        }
        return result;

    }
}
