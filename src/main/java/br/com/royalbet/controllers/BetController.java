package br.com.royalbet.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.royalbet.models.Bet;
import br.com.royalbet.models.User;
import br.com.royalbet.services.BetService;

@Controller
@RequestMapping("/bet")
public class BetController {

	@Autowired
	private BetService service;

	@RequestMapping("/form")
	public String getBetForm(Bet sorteio, Model model, HttpSession session) {
		List<Bet> list = service.findByIdUser(session);
		List<Bet> favorites = list.stream()
				.filter(b-> b.isFavorite() == true)
				.collect(Collectors.toList());
		model.addAttribute("sorteio", sorteio);
		model.addAttribute("favoritos", favorites);
		return "bet/form";
	}

	@GetMapping("/myBets")
	public String listBets(Model model, HttpSession session){
		List<Bet> list = service.findByIdUser(session);
		model.addAttribute("bets", list);
        return "bet/list";	
    }

	/*@GetMapping(value = "/bets/{id}")
	public String listBetId(@PathVariable(value = "id") Long id, ModelAndView model, RedirectAttributes redirectAttributes) {
		Bet bet = service.findById(id);
		if (bet == null) {
			redirectAttributes.addFlashAttribute("mensagem", "Sorteio não encontrado");
			model.addAttribute("bet", new Bet());
		} else {
			model.addAttribute("bet", bet);
		}
		return "bet/form";
	}
	 */


	@PostMapping(value = "/create")
	public ModelAndView createBet(Bet bet, ModelAndView model,RedirectAttributes redirectAttributes, HttpSession session ){
		User usuario = (User) session.getAttribute("user");
		if (bet.getNumbers() == null) {
			redirectAttributes.addFlashAttribute("mensagem", "Prencha os campos obrigatórios");
			model.setViewName("redirect:/bet/form");
			return model;
		} else {
			service.fazerAposta(bet);
			bet.setUser(usuario);
			service.insert(bet);
			redirectAttributes.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso");
			model.setViewName("redirect:/bet/myBets");
			return model;
		}
	}
	
	@RequestMapping("/{id}/setFavorite")
	public String setOperator(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
		service.changeFavorite(id);
		List<Bet> list = service.findByIdUser(session);
		model.addAttribute("bets", list);
		return "redirect:/bet/myBets";
	}


}
