package br.com.royalbet.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.royalbet.models.Sorteio;
import br.com.royalbet.services.BetService;
import br.com.royalbet.services.SorteioService;

@Controller
@RequestMapping("/sorteio")
public class SorteioController {
	
	@Autowired
	private SorteioService service;
	
	@Autowired
	private BetService betService;
	
	@GetMapping("/sorteioOperador")
    public String homeSorteioViewOperador(Model model){
		List<Sorteio> list = service.findAll();
		model.addAttribute("sorteios", list);
        return "sorteio/sorteioOperador";	
    }
	
	@GetMapping("/sorteioCliente")
    public String homeSorteioViewCliente(Model model){
		List<Sorteio> list = service.findAll();
		model.addAttribute("sorteios", list);
        return "sorteio/sorteioCliente";	
    }
	
//	@ModelAttribute("apostas")
//	public List<String> getApostas(HttpSession session) {
//		List<Bet> bets = betService.findByIdUser(session);
//		List<String> numbers = new ArrayList<>();
//		for (Bet b: bets) {
//			numbers.add(b.getNumbers());
//		}
//		return numbers;
//	}
	
	@RequestMapping("/form")
	public ModelAndView getSorteioForm(ModelAndView modelAndView) {
		modelAndView.setViewName("sorteio/form");
		modelAndView.addObject("sorteio", new Sorteio());
		return modelAndView;
	}
	
	@RequestMapping("/gerar")
	public ModelAndView getNumbers(ModelAndView modelAndView) {
		modelAndView.setViewName("sorteio/form");
		Sorteio sorteio = new Sorteio();
		Set<Integer> list = service.gerarSorteio();
		String numbers = list.toString();
		numbers = numbers.replace("[", " ");
		numbers = numbers.replace("]", " ");
		numbers = numbers.trim();
		sorteio.setNumbers(numbers);
		modelAndView.addObject("sorteio", sorteio);
		return modelAndView;
	}
	
	@PostMapping("/create")
	public String createSorteio(Sorteio sorteio) {
		service.insert(sorteio);
		return "redirect:/sorteio/sorteioOperador";
	}
	
}
