package br.com.royalbet.services;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.royalbet.models.Sorteio;
import br.com.royalbet.repositories.SorteioRepository;
import br.com.royalbet.services.exceptions.ResourceNotFoundException;

@Service
public class SorteioService {

	@Autowired
	private SorteioRepository repository;

	public Set<Integer> gerarSorteio() {
		Set<Integer> aposta = null;
		aposta = new LinkedHashSet<Integer>(6);
		while (aposta.size() < 6) {
			aposta.add(ThreadLocalRandom.current().nextInt(1, 61));
		}

		return aposta;
	}

	public List<Sorteio> findAll() {
		return repository.findAll();
	}

	public Sorteio findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public void insert(Sorteio obj) {
		repository.save(obj);
	}

}
