package com.edu.nju.asi.controller;

import com.edu.nju.asi.model.OptionalStockID;
import com.edu.nju.asi.model.PrivateStock;
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


    /**
     * 修改用户信息
     */
    @PostMapping
    public ModelAndView modify() {
        return null;
    }

    @GetMapping("addPrivate/{stockCode}")
    @ResponseBody
    public String addPrivateStock(@PathVariable String stockCode, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        OptionalStockID optionalStockID = new OptionalStockID(user.getUserName(), stockCode);
        try {
            if (privateStockService.addPrivateStock(optionalStockID)){
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
    public String deletePrivateStock(@PathVariable String stockCode, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println(user.getUserName());
        OptionalStockID optionalStockID = new OptionalStockID(user.getUserName(), stockCode);

        if (privateStockService.deletePrivateStock(optionalStockID)){
            //表示成功
            System.out.println("删除自选股成功");
            return "1";
        }
        else {
            return "0";
        }

    }

}
