package br.com.royalbet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.royalbet.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);

    @Query("SELECT u FROM User u WHERE u.id != ?1")
    List<User> findOthers(Long id);
}
