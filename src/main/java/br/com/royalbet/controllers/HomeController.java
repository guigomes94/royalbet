package br.com.royalbet.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.royalbet.models.User;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ModelAndView getHome(ModelAndView model) {
        model.setViewName("/home");
        return model;
    }
    
    @GetMapping("/user/{id}")
	public String findById(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
    	User logado = (User) session.getAttribute("user");
		if (logado == null) {
			model.addAttribute("mensagem", "Usuario não encontrado");
			model.addAttribute("user", new User());
		} else {
			model.addAttribute("user", logado);
		}
		return "user/logado";
	}

}
