package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.utilities.LocalDateHelper;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihua on 2017/5/13.
 */
@Controller
@RequestMapping("/traceback")
public class TraceBackController {

    @Autowired
    TraceBackService traceBackService;

    /**
     * 通过选择的条件进行股票回测
     */
    @GetMapping
    public ModelAndView traceBack(@RequestBody TraceBackCriteria traceBackCriteria, HttpServletRequest request, HttpServletResponse response) {
        // TODO  又：可以通过json传输
//        String date1 = request.getParameter("startDate");
//        String date2 = request.getParameter("endDate");
//        String fp = request.getParameter("fp");
//        String hp = request.getParameter("hp");
//
//        LocalDate start = LocalDateHelper.convertString(date1);
//        LocalDate end = LocalDateHelper.convertString(date2);
//        int formativePeriod = Integer.parseInt(fp);
//        int holdingPeriod = Integer.parseInt(hp);

        TraceBackCriteria criteria = new TraceBackCriteria();

        List<String> stockPool = new LinkedList<>();


        ModelAndView mv = new ModelAndView("traceBack");
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
