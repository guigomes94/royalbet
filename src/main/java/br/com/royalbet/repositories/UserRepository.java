package br.com.royalbet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.royalbet.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface UserRepository extends JpaRepository<User, Long> {}
