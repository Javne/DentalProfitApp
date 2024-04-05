package com.javne.dentalprofitapp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home"; // Zwraca nazwę pliku HTML, np. "home.html"
    }
}