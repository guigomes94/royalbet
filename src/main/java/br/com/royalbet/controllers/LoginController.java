package br.com.royalbet.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.royalbet.models.User;
import br.com.royalbet.repositories.UserRepository;
import br.com.royalbet.util.PasswordUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getForm(ModelAndView modelAndView) {
		modelAndView.setViewName("login/login");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView valide(User user, HttpSession session, ModelAndView modelAndView,
			RedirectAttributes redirectAttributes) {
		if ((user = this.isValido(user)) != null) {
			session.setAttribute("user", user);
			modelAndView.setViewName("redirect:/home");
		} else {
			redirectAttributes.addFlashAttribute("mensagem", "Login e/ou senha inválidos!");
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/out")
	public ModelAndView logout(ModelAndView mav, HttpSession session) {
		session.invalidate();
		mav.setViewName("redirect:/login");
		return mav;
	}

	public User isValido(User user) {

		User userBanco = userRepository.findByCpf(user.getCpf());
		boolean valido = false;
		if (userBanco != null) {
			if (PasswordUtil.checkPass(user.getPassword(), userBanco.getPassword())) {
				valido = true;
			}
		}
		return valido ? userBanco : null;
	}
}
