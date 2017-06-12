package com.edu.nju.asi.controller;

import com.edu.nju.asi.infoCarrier.strategy.GeneralStrategy;
import com.edu.nju.asi.model.OptionalStockID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.PrivateStockService;
import com.edu.nju.asi.service.StrategyService;
import com.edu.nju.asi.service.UserService;
import com.edu.nju.asi.utilities.exceptions.PasswordInputException;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;
import com.edu.nju.asi.utilities.exceptions.TraceBackStockExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PrivateStockService privateStockService;

    @Autowired
    StrategyService strategyService;


    // 因为可能数据库中没有今天的数据，所以默认显示昨日
    private final static LocalDate defaultDate = LocalDate.now().minusDays(1);

    /**
     * 普通用户登录初始界面，需展示用户基本信息和用户自选股列表
     * TODO 管理员未处理
     */
    @GetMapping("/welcome")
    public ModelAndView welcome(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("index");
        }

        String userType = (String) session.getAttribute("userType");
        System.out.println(userType);
        if (userType.equals("user")) {
            // 普通用户
            ModelAndView mv = new ModelAndView("userManager");
            User user = (User) session.getAttribute("user");

            // 自选股信息
            List<Stock> psList = privateStockService.getPrivateStocks(user.getUserName(), defaultDate);
            for (Stock stock : psList) {
                System.out.println(stock.getStockID().getCode() + " " + stock.getName());
            }


            // 股票策略信息
            List<GeneralStrategy> myOwn = new LinkedList<>();
            List<GeneralStrategy> mySubscribe = new LinkedList<>();

            List<Strategy> myStrategies = strategyService.getMyStrategies(user);
            for (Strategy temp : myStrategies) {
                if (temp.getCreator().equals(user.getUserName())) myOwn.add(new GeneralStrategy(temp));
                else mySubscribe.add(new GeneralStrategy(temp));
            }

            System.out.println("psList size: " + psList.size());
            System.out.println("myOwn size: " + myOwn.size());
            System.out.println("mySubscribe size: " + mySubscribe.size());

            mv.addObject("ps_list", psList);
            mv.addObject("myOwn", myOwn);
            mv.addObject("mySubscribe", mySubscribe);
            return mv;

        } else if (userType.equals("admin")) {
            // 管理员 TODO
            return new ModelAndView("welcome_admin");
        }

        return new ModelAndView("index");
    }


    /**
     * 修改用户信息
     */
    @PostMapping(value = "/modify", produces = "text/html;charset=UTF-8;")
    public @ResponseBody
    String modify(@RequestParam("user") User modifiedUser, HttpServletRequest request) {
        // 限制进入
        HttpSession session = request.getSession(false);
        User thisUser = (User) request.getSession().getAttribute("user");
        if (session == null || thisUser == null) {
            System.out.println("未登录");
            return "-1;未登录";
        }

        System.out.println("已登录：" + thisUser.getUserName());
        System.out.println(modifiedUser.getUserName() + " " + modifiedUser.getPassword() + " " + modifiedUser.getEmail());

        boolean modifyResult = false;
        try {
            modifyResult = userService.modifyUser(modifiedUser);
        } catch (PasswordInputException e) {
            e.printStackTrace();
            return "-1;密码输入不合法";
        }

        if (modifyResult) return "1;修改成功";
        else return "-1;修改失败";
    }

    /**
     * 增加自选股
     */
    @GetMapping("addPrivate/{stockCode}")
    @ResponseBody
    public String addPrivateStock(@PathVariable String stockCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        OptionalStockID optionalStockID = new OptionalStockID(user.getUserName(), stockCode);
        try {
            if (privateStockService.addPrivateStock(optionalStockID)) {
                //表示成功
                System.out.println("添加自选股成功");
                return "1";
            }
        } catch (PrivateStockExistedException e) {
            System.out.println("添加自选股失败");
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 删除自选股
     */
    @GetMapping("deletePrivate/{stockCode}")
    @ResponseBody
    public String deletePrivateStock(@PathVariable String stockCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user.getUserName());
        OptionalStockID optionalStockID = new OptionalStockID(user.getUserName(), stockCode);

        if (privateStockService.deletePrivateStock(optionalStockID)) {
            //表示成功
            System.out.println("删除自选股成功");
            return "1";
        } else {
            return "-1";
        }

    }

    @PostMapping("/modify_my_trace_back_pool")
    public @ResponseBody
    String modifyMyTraceBackPool(@RequestParam("myTraceBackPool") List<String> myTraceBackPool,
                                 HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user.getUserName());

        try {
            userService.modifyMyTraceBackPool(myTraceBackPool, user);
            return "1;修改成功";
        } catch (TraceBackStockExistedException e) {
            return "-1;" + e.getMessage();
        }
    }


}
