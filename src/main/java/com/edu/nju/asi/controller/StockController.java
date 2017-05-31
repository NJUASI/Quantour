package com.edu.nju.asi.controller;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.ChartShowCriteria;
import com.edu.nju.asi.infoCarrier.StockComparision;
import com.edu.nju.asi.infoCarrier.StockComparisionCriteria;
import com.edu.nju.asi.infoCarrier.StocksPage;
import com.edu.nju.asi.model.*;
import com.edu.nju.asi.service.ChartService;
import com.edu.nju.asi.service.PrivateStockService;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.StockSituationService;
import com.edu.nju.asi.utilities.NumberFormat;
import com.edu.nju.asi.utilities.StockCodeHelper;
import com.edu.nju.asi.utilities.enums.StocksSortCriteria;
import com.edu.nju.asi.utilities.exceptions.CodeNotFoundException;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
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
    @Autowired
    PrivateStockService privateStockService;


    // 因为可能数据库中没有今天的数据，所以默认显示昨日
//    private final static LocalDate defaultDate = LocalDate.now().minusDays(1);
    private final static LocalDate defaultDate = LocalDate.of(2017, 2, 3);

    /**
     * 默认页面跳转，默认日期股票市场查看（所有股票数据、市场温度计）
     */
    @GetMapping()
    public ModelAndView getStockMarket(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        System.out.println("默认页面跳转进来的");

        String reqResult = reqGetStockMarket(defaultDate, StocksSortCriteria.CODE_ASC, 1);
        System.out.println("请求成功");

        String[] parts = reqResult.split(";");
        if (parts[0].equals("1")) {
            // 解析JSON对象
            StocksPage page = JSON.parseObject(parts[1], StocksPage.class);
//            String topClicksChart = parts[2];
            System.out.println(page.stocks.size() + "\n\n\n");

            ModelAndView mv = new ModelAndView("stocks");
            mv.addObject("base_stock_list", page.baseStocks);
            mv.addObject("stock_list", page.stocks);
            mv.addObject("date", page.thisDate);
            mv.addObject("numOfEachPage", page.numOfEachPage);
            mv.addObject("curPageNum", page.curPageNum);
            mv.addObject("totalPageNum", page.totalPageNum);
            mv.addObject("totalRecordNum", page.totalRecordNum);
            mv.addObject("topClicks", page.topClicks);
//            mv.addObject("topClicksChartData", topClicksChart);
            return mv;
        } else {
            System.out.println("请求失败");
            return new ModelAndView("errorPage");
        }
    }


    /**
     * 【请求】指定日期股票市场查看（所有股票数据、市场温度计），返回后JS修改页面的数据
     */
    @PostMapping(produces = "text/html;charset=UTF-8;")
    public @ResponseBody
    String reqGetStockMarket(@RequestParam("date") LocalDate thisDate, @RequestParam("sortCriteria") StocksSortCriteria comparisionCriteria,
                             @RequestParam("wantedPage") int wantedPage) {
        System.out.println("--------在req中-----------");
        System.out.println(thisDate + "\n" + comparisionCriteria.getRepre() + "\n" + wantedPage);

        String result = null;
        try {
            List<Stock> allStocks = stockService.getAllStocks(thisDate, comparisionCriteria);
            List<BaseStock> baseStocks = stockService.getBaseStockDataOfOneDay(thisDate);
            System.out.println(allStocks.size() + "   " + baseStocks.size());

            List<Stock> wantedStocks;
            if ((wantedPage - 1 == allStocks.size() / 80) && (allStocks.size() % 80 != 0)) {
                wantedStocks = allStocks.subList((wantedPage - 1) * 80, allStocks.size());
            } else {
                wantedStocks = allStocks.subList((wantedPage - 1) * 80, wantedPage * 80);
            }

            // 显示热搜榜（前10和图表）
            List<StockSearch> topSearched = stockService.getTopRankingList(10);


            StocksPage wanted = new StocksPage(thisDate, 80, wantedPage, allStocks.size() / 80 + 1,
                    allStocks.size(), baseStocks, wantedStocks, topSearched);
            result = JsonConverter.convertStockMarket(wanted);

            System.out.println("Success\nwantedStocks size: " + wantedStocks.size());
            return "1;" + result;

        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        }
    }

    /**
     * 单只股票的股票详情
     */
    @GetMapping("/{id}")
    public ModelAndView getOneStock(@PathVariable("id") String stockCode, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println("默认页面跳转进来的");

        // 默认显示默认日期以前一年的K线图
        String reqResult = reqGetOneStock(stockCode, defaultDate.minusYears(1), defaultDate, request);

        String[] parts = reqResult.split(";");
        if (parts[0].equals("1")) {
            System.out.println("请求成功");

            // 解析JSON对象
            String candlestickData = parts[1];
            String volumeData = parts[2];
            Stock stockOfEndDay = JSON.parseObject(parts[3], Stock.class);
            LocalDate startDate = JSON.parseObject(parts[4], LocalDate.class);
            boolean isPrivate = JSON.parseObject(parts[5], Boolean.class);
//            String clickedData = parts[6];

            // 搜索量加一
            SearchID thisStock = new SearchID(stockOfEndDay.getStockID().getCode(), stockOfEndDay.getName());
            boolean clicked = stockService.addClickAmount(thisStock);
            if (!clicked) {
                System.err.println("getOneStock：搜索量未加一！！");
            }


            ModelAndView mv = new ModelAndView("kString");
            mv.addObject("candlestickData", candlestickData);
            mv.addObject("volumeData", volumeData);
            mv.addObject("stockOfEndDay", stockOfEndDay);
            mv.addObject("startDate", startDate);
            mv.addObject("isPrivate", isPrivate);
//            mv.addObject("clickedData", clickedData);
            return mv;
        } else {
            System.out.println("请求失败");
            return new ModelAndView("errorPage");
        }
    }


    /**
     * 【请求】单只股票的股票详情，返回后JS修改页面的数据
     */
    @PostMapping(value = "/{id}", produces = "text/html;charset=UTF-8;")
    public @ResponseBody
    String reqGetOneStock(@PathVariable("id") String stockCode, @RequestParam("startDate") LocalDate startDate,
                          @RequestParam("endDate") LocalDate endDate, HttpServletRequest request) {
        System.out.println("--------在req中-----------");
        System.out.println(stockCode + "\n" + startDate + "\n" + endDate);

        String result = null;
        try {
            // 股票数据
            List<Stock> stocks = chartService.getSingleStockRecords(new ChartShowCriteria(StockCodeHelper.format(stockCode), startDate, endDate));

            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");

            // 是否为自选股
            boolean isPrivate = false;
            if (user != null) {
                // 用户未登录，默认isPrivate为false
                // 用户登录才进行处理：根据用户姓名获取用户对应的所有自选股，实时显示
                List<PrivateStock> privateCodes = privateStockService.getPrivateStock(user.getUserName());
                for (int i = 0; i < privateCodes.size(); i++) {
                    if (privateCodes.get(i).getOptionalStockID().getStockCode().equals(stockCode)) {
                        isPrivate = true;
                        break;
                    }
                }
            }

            // 当前股票热度
            double nowClickNum = stockService.getClickRate(new SearchID(stockCode, stocks.get(0).getName()));

            result = JsonConverter.convertOneStock(stocks, isPrivate, nowClickNum);

            System.out.println("Success\nstocks size: " + stocks.size());
            return "1;" + result;

        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        } catch (CodeNotFoundException | NoDataWithinException | DateNotWithinException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        }
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

        // 默认界面跳转进来的
        return new ModelAndView("stockComparision");
    }


    /**
     * 【请求】比较两只股票
     */
    @PostMapping(value = "/req_compare", produces = "text/html;charset=UTF-8;")
    public @ResponseBody
    String reqCompare(@RequestParam("comparisionCriteria") StockComparisionCriteria criteria, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
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

        System.out.println(criteria.stockCode1 + "  " + criteria.stockCode2 + "  " + criteria.start + "  " + criteria.end);

        List<StockComparision> result = null;
        String closes01, closes02, logarithmicYield01, logarithmicYield02, comparisionName, numVals;
        try {
            result = chartService.getComparision(criteria);
            // TODO 加入当日信息显示，与界面沟通

            closes01 = JsonConverter.convertComparision(result.get(0).closes);
            closes02 = JsonConverter.convertComparision(result.get(1).closes);
            logarithmicYield01 = JsonConverter.convertComparision(result.get(0).logarithmicYield);
            logarithmicYield02 = JsonConverter.convertComparision(result.get(1).logarithmicYield);
            List<String> name = new ArrayList<>();
            name.add(result.get(0).name);
            name.add(result.get(1).name);
            comparisionName = JsonConverter.jsonOfObject(name);

            List<List<String>> numVal = new ArrayList<>();
            numVal.add(this.convertComparisionNumVal(result.get(0)));
            numVal.add(this.convertComparisionNumVal(result.get(1)));
            numVals = JsonConverter.jsonOfObject(numVal);


            return closes01 + ";" + closes02 + ";" + logarithmicYield01 + ";" + logarithmicYield02 + ";" + comparisionName + ";" + numVals;
        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        } catch (DataSourceFirstDayException | DateNotWithinException | NoDataWithinException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        }
    }


    @GetMapping(value = "/search", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String searchStocks(@RequestParam("key") String keyword, HttpServletRequest request) {
        List<StockSearch> results = stockService.searchStock(keyword);
        try {
            String result = JsonConverter.jsonOfObject(results);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "-1";
    }


    private List<String> convertComparisionNumVal(StockComparision comparision) {
        List<String> result = new LinkedList<>();
        result.add(comparision.name);
        result.add(NumberFormat.decimaFormat(comparision.max, 4));
        result.add(NumberFormat.decimaFormat(comparision.min, 4));
        result.add(NumberFormat.percentFormat(comparision.increaseMargin, 2));
        result.add(NumberFormat.decimaFormat(comparision.logarithmicYieldVariance, 4));
        return result;

    }

}
