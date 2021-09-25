package br.com.royalbet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.royalbet.models.User;

@Controller
public class MainController {

	@RequestMapping(value="/")
    public String homePage(Model model){
		model.addAttribute("user", new User());
        return "login/login";	
    }
}
