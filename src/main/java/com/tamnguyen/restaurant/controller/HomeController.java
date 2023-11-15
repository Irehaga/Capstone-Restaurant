package com.tamnguyen.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Tam Nguyen
 */
@Controller
public class HomeController {

    @GetMapping("/index")
    public String index(){

        return "index";
    }
}
