package com.edu.nju.asi.controller;

import com.edu.nju.asi.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuihua on 2017/5/12.
 */
@Controller
public class UserController {

    @GetMapping("/welcome")
    public ModelAndView welcome(@RequestParam String id) {
        ModelAndView result = new ModelAndView("welcome");

        User thisUser = new User();
        thisUser.setUserName(id);
        result.addObject("user", thisUser);
        return result;
    }

}
