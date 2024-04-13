package com.javne.dentalprofitapp.homeController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String list(){
        return "list";
    }

    @GetMapping("/doctorInfo")
    public String getDoctorInfo(){
        return "doctor";
    }

}