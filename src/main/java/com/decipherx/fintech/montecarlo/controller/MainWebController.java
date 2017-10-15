package com.decipherx.fintech.montecarlo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainWebController {


    @RequestMapping("/index")
    public String getIndexPage(){
        return "index";
    }
}
