package com.javne.dentalprofitapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/") // to jest endpoint do strony home
    public String home(){
        return "home";
    }
}
