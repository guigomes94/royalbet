package br.com.royalbet.services;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.royalbet.models.Bet;
import br.com.royalbet.repositories.BetRepository;
import br.com.royalbet.services.exceptions.DatabaseException;
import br.com.royalbet.services.exceptions.ResourceNotFoundException;

public class BetService {

    @Autowired
    private BetRepository repository;

    public List<Bet> findAll() {
        return repository.findAll();
    }

    public Bet findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Bet insert(Bet obj) {
    	Integer qtdDezenas = obj.getNumbers().split(",").length;
    	obj.setPrice(calcPrice(qtdDezenas));
        return repository.save(obj);
    }
    
    public Bet setFavorite(Long id, Bet obj) {
    	 try {
             Bet entity = repository.getById(id);
             entity.setFavorite(obj.isFavorite());
             return repository.save(entity);
         } catch (EntityNotFoundException e) {
             throw new ResourceNotFoundException(id);
         }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }
    
    private Double calcPrice(int qtdDezenas) {
    	switch(qtdDezenas) {
    	case 7:
    		return 15.0;
    	case 8:
    		return 90.0;
    	case 9:
    		return 300.0;
    	case 10:
    		return 1_200.0;
    	default:
    		return 3.0;
    	}
    	
    }

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
