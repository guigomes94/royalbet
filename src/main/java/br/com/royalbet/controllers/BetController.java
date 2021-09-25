package br.com.royalbet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.royalbet.models.Bet;
import br.com.royalbet.services.BetService;

@Controller
@RequestMapping("/bet")
public class BetController {

	@Autowired
	private BetService service;

	@RequestMapping("/form")
	public String getBetForm(Bet sorteio, Model model) {
		model.addAttribute("sorteio", sorteio);
		return "bet/form";
	}

	@GetMapping("/myBets")
	public String listBets(Model model){
		List<Bet> list = service.findByIdUser();
		model.addAttribute("apostas", list);
        return "bet/list";	
    }

	@GetMapping(value = "/bets/{id}")
	public String listBetId(@PathVariable(value = "id") Long id, Model model) {
		Bet bet = service.findById(id);
		if (bet == null) {
			model.addAttribute("mensagem", "Sorteio não encontrado");
			model.addAttribute("bet", new Bet());
		} else {
			model.addAttribute("bet", bet);
		}
		return "bet/form";
	}

	@PostMapping(value = "/create/bet")
	public String createBet(Bet bet, Model model) {
		if (bet.getDataBet() == null) {
			model.addAttribute("mensagem", "Prencha os campos obrigatórios");
			return "bet/form";
		} else {
			service.insert(bet);
			model.addAttribute("mensagem", "Sorteio cadastrado com sucesso");
			return "bet/bets";
		}
	}

}
