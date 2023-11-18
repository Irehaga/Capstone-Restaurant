package com.tamnguyen.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Tam Nguyen
 */
@Controller
public class HomeController {

    @GetMapping("/index")
    public String showIndexPage(){
        return "index";
    }


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

}
