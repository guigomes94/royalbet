package br.com.royalbet.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.royalbet.models.Bet;
import br.com.royalbet.models.User;
import br.com.royalbet.repositories.BetRepository;
import br.com.royalbet.services.exceptions.DatabaseException;
import br.com.royalbet.services.exceptions.ResourceNotFoundException;

@Service
public class BetService {

	@Autowired
	private BetRepository repository;

	public List<Bet> findAll() {
		return repository.findAll();
	}

	public Bet findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Bet insert(Bet obj) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        obj.setDataBet(date);
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

	public List<Bet> findByIdUser(HttpSession userSession) {
		User loginUser = (User) userSession.getAttribute("user");
		List<Bet> allBetsByUser = repository.findBetsByUser(loginUser);

		return allBetsByUser;
	}

	public void fazerAposta(Bet bet) {
		List<String> numbers = List.of(bet.getNumbers().split(","));
		Integer dezenas = numbers.size();
		Double price = priceDezenas(dezenas);
		bet.setPrice(price);
	}

	private Double priceDezenas(Integer dezenas) {
		HashMap<Integer, Double> price = new HashMap<>();
		price.put(6, 3.0);
		price.put(7, 15.0);
		price.put(8, 90.0);
		price.put(9, 300.0);
		price.put(10, 1200.0);

		return price.get(dezenas);

	}
	
	public Bet changeFavorite(Long id) {
		try {
			Bet entity = repository.getById(id);
			entity.setFavorite(!entity.isFavorite());
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
