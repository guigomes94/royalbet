package br.com.royalbet.controllers;

import java.util.Set;

import br.com.royalbet.models.Sorteio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.royalbet.services.SorteioService;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sorteio")
public class SorteioController {
	
	@Autowired
	private SorteioService service;


    @RequestMapping("/sorteiooperador")
    public ModelAndView homeSorteioViewOperador(ModelAndView model){
        model.setViewName("sorteio/sorteiooperador");
        Set<Integer> sorteio = service.gerarSorteio();
		model.addObject("sorteionumero", sorteio);
		model.addObject("sorteioform", new Sorteio());
        return model;
    }
	
	@GetMapping("/sorteiocliente")
    public String homeSorteioViewCliente(Model model){
        return "sorteio/sorteiocliente";
    }


    @PostMapping("/salvar")
    public String homeSorteioSalvar(ModelAndView model, Sorteio sorteio) {
        service.insert(sorteio);
        return "redirect:/home";
    }
}
