package br.com.royalbet.repositories;

import br.com.royalbet.models.Bet;
import br.com.royalbet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    @Query("SELECT b FROM Bet b WHERE b.user = ?1")
    List<Bet> findBetsByUser(User id);
}
