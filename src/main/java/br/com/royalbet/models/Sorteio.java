package br.com.royalbet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="tb_sorteio")
public class Sorteio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numbers;
	
	private String sorteioDate;
	
	private Double prize;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getSorteioDate() {
		return sorteioDate;
	}
	
	public void setSorteioDate(String date) {
		this.sorteioDate = date;
	}

	public Double getPrize() {
		return prize;
	}

	public void setPrize(Double value) {
		this.prize = value;
	}
	
	
}
