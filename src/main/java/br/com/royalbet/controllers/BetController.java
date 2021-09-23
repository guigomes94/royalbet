package br.com.royalbet.controllers;


import br.com.royalbet.models.Bet;
import br.com.royalbet.services.BetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Random;

import java.util.List;

@Controller
@RequestMapping("/bet")
public class BetController {

    BetService service;

    @RequestMapping("/form")
    public String getBetForm(Bet sorteio, Model model) {
        model.addAttribute("sorteio", sorteio);
        return "bet/form";
    }

    @GetMapping(value = "bets")
    public String listAllBets(Model model) {
        model.addAttribute("bets", service.findAll());
        return "bet/bets";
    }

    @GetMapping(value = "/bets{id}")
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

    /*@GetMapping("/bet/sorteia")
    public List generateSorteio(Model model){

    }

     */



    public List<String> sorteador(){
        List<String> numbers = null;
        Integer numberSort;

        for(int x = 1; x<=6;x++){
           // Adiciono o primeiro número devido o list está vazio
            if(numbers.isEmpty()){
                numbers.add(generateNumber().toString());
            }else{
                numberSort = generateNumber();
                // validação para não inserir o mesmo número mais vezes
                if(numberSort == 0 || numbers.contains(numberSort.toString())){
                    numberSort = generateNumber();
                    numbers.add(numberSort.toString());
                }
            }

        }

        return numbers;
    }


    public Integer generateNumber(){
        Random random = new Random();
        int number = random.nextInt(61);

        return number;
    }

}

