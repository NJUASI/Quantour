package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.traceBack.MaxTraceBack;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackNumVal;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.TraceBackStockPoolService;
import com.edu.nju.asi.utilities.NumberFormat;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import com.edu.nju.asi.utilities.tempHolder.TraceBackCriteriaTempHolder;
import com.edu.nju.asi.utilities.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
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
    @GetMapping("/trace_back_home")
    public String traceBackHome() {
        return "traceBackHome";
    }


    /**
     * 查看回测结果
     */
    @GetMapping("/trace_back")
    public ModelAndView traceBack(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("index");
        }

        TraceBackInfo traceBackInfo = (TraceBackInfo) session.getAttribute("traceBackResult");
        session.setAttribute("traceBackResult", null);

        if (traceBackInfo != null) {
            ModelAndView mv = new ModelAndView("traceBackHome");
            mv.addObject("traceBackNums", convertTraceBackNumVal(traceBackInfo));
            mv.addObject("abReturnPeriod", traceBackInfo.absoluteReturnPeriod);
            mv.addObject("reReturnPeriod", traceBackInfo.relativeReturnPeriod);
            mv.addObject("holdingDetails", traceBackInfo.holdingDetails);
            mv.addObject("certainFormates", traceBackInfo.certainFormates);
            mv.addObject("certainHoldings", traceBackInfo.certainHoldings);

            // 加入画图所需的信息
            try {
                mv.addObject("json_strategyData", JsonConverter.convertTraceBack(traceBackInfo.strategyCumulativeReturn));
                mv.addObject("json_baseData", JsonConverter.convertTraceBack(traceBackInfo.baseCumulativeReturn));
                mv.addObject("json_absoluteHistogramData", JsonConverter.convertHistogram(traceBackInfo.absoluteReturnPeriod));
                mv.addObject("json_relativeHistogramData", JsonConverter.convertHistogram(traceBackInfo.relativeReturnPeriod));

                List<String> formate = JsonConverter.convertExcessAndWin(traceBackInfo.certainFormates);
                List<String> holdings = JsonConverter.convertExcessAndWin(traceBackInfo.certainHoldings);
                mv.addObject("json_certainFormatesExcessData", formate.get(0));
                mv.addObject("json_certainFormatesWinData", formate.get(1));
                mv.addObject("json_certainHoldingsExcessData", holdings.get(0));
                mv.addObject("json_certainHoldingsWinData", holdings.get(1));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return mv;
        }
        return new ModelAndView("index");
    }


    /**
     * 通过选择的条件，请求进行股票回测
     */
    @PostMapping(value = "/req_trace_back", produces = "text/html;charset=UTF-8;application/json")
    public @ResponseBody
    String reqTraceBack(@RequestBody TraceBackCriteriaTempHolder criteriaTempHolder, HttpServletRequest request, HttpServletResponse response) {
        // 限制进入
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("未登录");

            try {
                response.sendRedirect("/welcome");
            } catch (IOException e) {
                e.printStackTrace();
                return "-1;不给看";
            }
            return "-1;未知错误";
        }

        User thisUser = (User) request.getSession().getAttribute("user");
        System.out.println("已登录：" + thisUser.getUserName());

        TraceBackCriteria criteria = new TraceBackCriteria(criteriaTempHolder);
        List<String> stockPool = stockPoolService.getTraceBackStockPoolCodes(thisUser.getUserName());

        System.out.println(criteria.startDate + "  " + criteria.endDate + "  " + criteria.formateAndPickCriteria.rank + "  " + criteria.holdingPeriod);


        TraceBackInfo traceBackInfo = null;
        try {
            traceBackInfo = traceBackService.traceBack(criteria, stockPool);
        session.setAttribute("traceBackResult", traceBackInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        } catch (DataSourceFirstDayException | DateNotWithinException | NoDataWithinException | UnhandleBlockTypeException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        }

        if (traceBackInfo != null) {
            System.out.println("Success");
        return "1;回测成功";
        } else return "-1;服务器开了一个小差。。请稍后重试";
    }


    private List<String> convertTraceBackNumVal(TraceBackInfo info) {
        TraceBackNumVal val = info.traceBackNumVal;
        MaxTraceBack maxTraceBack = info.maxTraceBack;
        List<String> result = new LinkedList<>();

        // 策略部分
        result.add(NumberFormat.percentFormat(val.sumRate, 2));
        result.add(NumberFormat.percentFormat(val.annualizedRateOfReturn, 2));
        result.add(NumberFormat.decimaFormat(val.sharpeRatio, 4));
        result.add(NumberFormat.percentFormat(maxTraceBack.maxStrategyTraceBackRate, 2));
        result.add(NumberFormat.percentFormat(val.returnVolatility, 2));
        result.add(NumberFormat.decimaFormat(val.beta, 4));
        result.add(NumberFormat.decimaFormat(val.alpha, 4));

        // 基准部分
        result.add(NumberFormat.percentFormat(val.baseSumRate, 2));
        result.add(NumberFormat.percentFormat(val.baseAnnualizedRateOfReturn, 2));
        result.add(NumberFormat.decimaFormat(val.baseSharpeRatio, 4));
        result.add(NumberFormat.percentFormat(maxTraceBack.maxBaseTraceBackRate, 2));
        result.add(NumberFormat.percentFormat(val.baseReturnVolatility, 2));
        result.add("/");
        result.add("/");

        // 相对部分
        result.add(NumberFormat.percentFormat(val.sumRate - val.baseSumRate, 2));
        result.add(NumberFormat.percentFormat(val.annualizedRateOfReturn - val.baseAnnualizedRateOfReturn, 2));
        result.add(NumberFormat.decimaFormat(val.sharpeRatio - val.baseSharpeRatio, 4));
        result.add(NumberFormat.percentFormat(maxTraceBack.maxStrategyTraceBackRate - maxTraceBack.maxBaseTraceBackRate, 2));
        result.add(NumberFormat.percentFormat(val.returnVolatility - val.baseReturnVolatility, 2));
        result.add(NumberFormat.decimaFormat(val.beta - val.baseReturnVolatility, 4));
        result.add(NumberFormat.decimaFormat(val.alpha - val.baseReturnVolatility, 4));


        for (String tempStr: result) {
            System.out.println(tempStr);
        }


        return result;
    }

}
