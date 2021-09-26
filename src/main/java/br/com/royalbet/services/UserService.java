package br.com.royalbet.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.royalbet.models.User;
import br.com.royalbet.repositories.UserRepository;
import br.com.royalbet.services.exceptions.DatabaseException;
import br.com.royalbet.services.exceptions.ResourceNotFoundException;
import br.com.royalbet.util.PasswordUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public List<User> findAllOthers(Long id) {
		return repository.findOthers(id);
	}
	
	public User findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}	 
	
	public User findByCpf(String cpf) {
		User user = repository.findByCpf(cpf);
		if (user != null) {
			return user;
		}
		return null;
	}
	
	public User insert(User obj) {
		obj.setPassword(PasswordUtil.hashPassword(obj.getPassword()));
		return repository.save(obj);
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public User changeOperator(Long id) {
		try {
			User entity = repository.getById(id);
			entity.setOperator(!entity.isOperator());
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setPassword(PasswordUtil.hashPassword(obj.getPassword()));
		entity.setBirthDate(obj.getBirthDate());
		entity.setCpf(obj.getCpf());
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
