package br.com.royalbet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.royalbet.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);
}
