package br.com.royalbet.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.royalbet.services.SorteioService;

@Controller
@RequestMapping("/sorteio")
public class SorteioController {
	
	@Autowired
	private SorteioService service;
	
	@GetMapping("/sorteioOperador")
    public String homeSorteioViewOperador(Model model){
		Set<Integer> sorteio = service.gerarSorteio();
		model.addAttribute("sorteio", sorteio);
        return "sorteio/sorteioOperador";	
    }
	
	@GetMapping("/sorteioCliente")
    public String homeSorteioViewCliente(Model model){
        return "sorteio/sorteioCliente";	
    }
}
