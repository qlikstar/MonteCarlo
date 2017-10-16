package com.decipherx.fintech.montecarlo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Controller
public class MainWebController {

    @RequestMapping(value ="/index", method=RequestMethod.GET)
    public String getIndexPage(){
        return "index";
    }
}
