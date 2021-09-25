package br.com.royalbet.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.royalbet.models.User;
import br.com.royalbet.models.UserForm;
import br.com.royalbet.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping("/form")
	public ModelAndView getUserForm(ModelAndView modelAndView) {
		modelAndView.setViewName("user/form");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@GetMapping(value = "/form/{id}")
	public String editUsuario(@PathVariable(value = "id") Long id, Model model) {
		User usuario = service.findById(id);
		if (usuario == null) {
			model.addAttribute("mensagem", "Usuario não encontrado");
			model.addAttribute("user", new User());
		} else {
			model.addAttribute("usuario", usuario);
		}
		return "user/form";
	}

	@PostMapping(value = "/create")
	public String createUser(@Valid User usuario, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Prencha os campos obrigatórios");
			return "user/form";
		} else {
			service.insert(usuario);
			redirectAttributes.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso");
			return "redirect:login/login";
		}	
	}

}
