package br.com.fiap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_produtora")
public class Produtora {
	
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "TBL_PRODUTORA_SEQ")
	@SequenceGenerator(
			name = "TBL_PRODUTORA_SEQ",
			sequenceName = "TBL_PRODUTORA_SEQ",
			allocationSize = 1)
	@Column(name = "nr_sequencia")
	private Long id;
	
	@Column(name = "nome_produtora", length = 100)
	private String nomePodutora;
	
	@Column(name = "cidade_produtora", length = 100)
	private String cidadeProdutora;
	
	
	@OneToMany(mappedBy = "produtora")
	private List<Game> game; 
	
	
	public Produtora () {}


	public Produtora(String nomePodutora, String cidadeProdutora) {
		this.nomePodutora = nomePodutora;
		this.cidadeProdutora = cidadeProdutora;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomePodutora() {
		return nomePodutora;
	}


	public void setNomePodutora(String nomePodutora) {
		this.nomePodutora = nomePodutora;
	}


	public String getCidadeProdutora() {
		return cidadeProdutora;
	}


	public void setCidadeProdutora(String cidadeProdutora) {
		this.cidadeProdutora = cidadeProdutora;
	}

	

	public List<Game> getGame() {
		return game;
	}


	public void setGame(List<Game> game) {
		this.game = game;
	}


	@Override
	public String toString() {
		return "Produtora [id=" + id + ", nomePodutora=" + nomePodutora + ", cidadeProdutora=" + cidadeProdutora
				+ ", game=" + game + "]";
	}



	
	
	
	
	
}
