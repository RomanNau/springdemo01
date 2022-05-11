package org.romannau.springdemo01.controllers;

import org.romannau.springdemo01.USDRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@EnableScheduling

public class DemoController {
    @Autowired
    USDRate usdrate = new USDRate();




    @GetMapping("/currencyRate")

    public String demo(Model model) throws IOException{
        model.addAttribute("dollarCourse", usdrate.getDollarCourse());
        return "home";
    }
}
