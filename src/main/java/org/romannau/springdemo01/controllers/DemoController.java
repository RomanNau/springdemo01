package org.romannau.springdemo01.controllers;

import org.romannau.springdemo01.myService.USDRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@EnableScheduling
public class DemoController {
    @Autowired
    USDRate usdrate = new USDRate();

    @GetMapping("/home")
    public String demo(Model model) throws IOException{
        model.addAttribute("dollarCourse", usdrate.getDollarCourse());

        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal). getUsername();
            model.addAttribute("Username", username);
        } else {
            String username = principal. toString();
            model.addAttribute("Username", username);
        }
        return "home";
    }
    @GetMapping("/index")
    public String index() {
        return  "index";
    }
}
