package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.TraceBackStockPoolService;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import com.edu.nju.asi.utilities.tempHolder.TraceBackCriteriaTempHolder;
import com.edu.nju.asi.utilities.util.JsonConverter;
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
    public String traceBack(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }

        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "traceBackHome";
        }
        return "index";
    }


    /**
     * 通过选择的条件，请求进行股票回测
     */
    @PostMapping(value = "/req_trace_back", produces = "text/html;charset=UTF-8;application/json")
    public @ResponseBody
    String reqTraceBack(@RequestBody TraceBackCriteria criteria, HttpServletRequest request, HttpServletResponse response) {
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

        System.out.println(criteria.startDate + "\n" + criteria.endDate);
//        TraceBackCriteria criteria = new TraceBackCriteria(criteriaTempHolder);
        List<String> stockPool = stockPoolService.getTraceBackStockPoolCodes(thisUser.getUserName());

        System.out.println(criteria.startDate + "  " + criteria.endDate + "  " + criteria.formateAndPickCriteria.rank + "  " + criteria.holdingPeriod);


        TraceBackInfo traceBackInfo = null;
        try {
            traceBackInfo = traceBackService.traceBack(criteria, stockPool);
        } catch (IOException e) {
            e.printStackTrace();
            return "-1;IO读取失败！";
        } catch (DataSourceFirstDayException | DateNotWithinException | NoDataWithinException | UnhandleBlockTypeException e) {
            e.printStackTrace();
            return "-1;" + e.getMessage();
        }

        if (traceBackInfo != null) {
            System.out.println("Success");
            return "1;" + JsonConverter.convertTraceBackInfo(traceBackInfo);
        } else return "-1;服务器开了一个小差。。请稍后重试";
    }


}
