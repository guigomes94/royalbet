package br.com.royalbet.services;

import br.com.royalbet.models.Bet;
import br.com.royalbet.models.User;
import br.com.royalbet.repositories.BetRepository;
import br.com.royalbet.repositories.UserRepository;
import br.com.royalbet.services.exceptions.DatabaseException;
import br.com.royalbet.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
        return repository.save(obj);
    }

    public Bet update(Long id, Bet obj) {
        try {
            Bet entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Bet entity, Bet obj) {
        // implementar

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
}
