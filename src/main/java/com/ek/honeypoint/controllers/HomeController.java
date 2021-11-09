package com.ek.honeypoint.controllers;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @Autowired
    DataSource dataSource;

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        try {
            System.out.println("home 요청");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "home!";
    }
}
