package br.com.royalbet.controllers;


import br.com.royalbet.models.User;
import br.com.royalbet.repositories.UserRepository;
import br.com.royalbet.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService service;

    @RequestMapping("/form")
    public String getUserForm(User usuario, Model model){
        model.addAttribute("usuario", usuario);
        return "usuario/form";

    }


    @GetMapping(value = "/users")
    public String listAllUsers(Model model){
        model.addAttribute("usuarios", service.findAll());
        return "user/users";

    }

    @GetMapping(value = "/users{id}")
    public String listUsersId(@PathVariable(value = "id") Long id,  Model model){
        User usuario = service.findById(id);
        if(usuario == null){
            model.addAttribute("mensagem", "Usuario não encontrado");
            model.addAttribute("user", new User());
        }else{
            model.addAttribute("usuario", usuario);
        }
        return "user/form";
    }


    @PostMapping(value = "/create/users")
    public String createUser(User usuario, Model model){
        if(usuario.getCpf() == null || usuario.getName() == null || usuario.getName() == null){
            model.addAttribute("mensagem", "Prencha os campos obrigatórios");
            return "user/form";
        }else{
            service.insert(usuario);
            model.addAttribute("mensagem", "Usuario cadastrado com sucesso");
            return "user/users";
        }
    }

}
