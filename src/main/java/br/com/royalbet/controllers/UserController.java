package br.com.royalbet.controllers;

import java.util.List;

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

	@GetMapping("/form/{id}")
	public String findById(@PathVariable(value = "id") Long id, Model model) {
		User usuario = service.findById(id);
		if (usuario == null) {
			model.addAttribute("mensagem", "Usuario não encontrado");
			model.addAttribute("user", new User());
		} else {
			model.addAttribute("user", usuario);
		}
		return "user/form";
	}
	
	@GetMapping("/admin")
	public String listUsuarios(Model model) {
		List<User> users = service.findAll();
		model.addAttribute("users", users);
		return "user/list";
	}

	@PostMapping("/create")
	public ModelAndView createUser(@Valid User usuario, BindingResult result, RedirectAttributes redirectAttributes,
			ModelAndView model) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Prencha os campos obrigatórios");
			model.setViewName("user/form");
			return model;

		} else {
			User u = service.findByCpf(usuario.getCpf());
			if (u != null) {
				service.update(u.getId(), usuario);
				redirectAttributes.addFlashAttribute("mensagem", "Usuário atualizado com sucesso");
			} else {
				service.insert(usuario);
				redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso");
			}
			model.setViewName("redirect:/login");
			return model;
		}
	}
	

}
