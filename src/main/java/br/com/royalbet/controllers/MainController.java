package br.com.royalbet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    // método para retornar a homepage
    @GetMapping ("/")
    public String homePage(){
        return "login/login";
    }
}
