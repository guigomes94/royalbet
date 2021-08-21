package br.com.royalbet.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "CLIENT")
public class Client extends User {
	
	@Transient
	private List<Bet> favoriteBets = new ArrayList<>();

	public List<Bet> getFavoriteBets() {
		return favoriteBets;
	}
	
	public void addFavoriteBet(Bet aposta) {
		favoriteBets.add(aposta);
	}

}
