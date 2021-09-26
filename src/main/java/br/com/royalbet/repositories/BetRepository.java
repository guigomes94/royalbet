package br.com.royalbet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.royalbet.models.Bet;
import br.com.royalbet.models.User;

public interface BetRepository extends JpaRepository<Bet, Long> {
	
	@Query("SELECT b FROM Bet b WHERE b.user = ?1")
    List<Bet> findBetsByUser(User id);
}
