package com.edu.nju.asi.controller;

import com.edu.nju.asi.model.OptionalStockID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.PrivateStockService;
import com.edu.nju.asi.service.UserService;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
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


    // 因为可能数据库中没有今天的数据，所以默认显示昨日 TODO 实际应用时改回去
    private final static LocalDate defaultDate = LocalDate.now().minusDays(1);
//    private final static LocalDate defaultDate = LocalDate.of(2017,2,3);

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
            try {
                List<Stock> psList = privateStockService.getPrivateStocks(user.getUserName(), defaultDate);

                for (Stock stock : psList) {
                    System.out.println(stock.getStockID().getCode() + " " + stock.getName());
                }

                System.out.println("psList size: " + psList.size());
                mv.addObject("ps_list", psList);
                return mv;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userType.equals("admin")) {
            // 管理员 TODO
            return new ModelAndView("welcome_admin");
        }

        return new ModelAndView("index");
    }


    /**
     * 修改用户信息
     */
    @PostMapping
    public ModelAndView modify() {
        return null;
    }

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
        return "1";
    }

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
            return "0";
        }

    }

}
