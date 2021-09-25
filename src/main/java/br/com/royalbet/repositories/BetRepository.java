package br.com.royalbet.repositories;

import br.com.royalbet.models.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
	
}
