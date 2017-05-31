package com.edu.nju.asi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuihua on 2017/5/31.
 * 热搜榜
 */
@Controller
@RequestMapping("/hot_search")
public class HotSearchController {

    /**
     * 跳转进热搜榜界面
     */
    @GetMapping
    public ModelAndView getHotSearchCharts() {
        return null;
    }

    /**
     * 【请求】在界面修改了查看标准，再次请求，返回后JS修改页面的数据
     */
    @PostMapping()
    public @ResponseBody
    String reqHotSearchCharts() {
        return null;
    }
}
