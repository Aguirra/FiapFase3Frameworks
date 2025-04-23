package br.com.fiap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_categoria")
public class Categoria {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "TBL_CATEGORIA_SEQ")
	@SequenceGenerator(
			name = "TBL_CATEGORIA_SEQ",
			sequenceName = "TBL_CATEGORIA_SEQ",
			allocationSize = 1)
	@Column(name = "nr_sequencia")
	private int id;
	
	@Column(name="nome_categoria")
	private String nomeCategoria;
	
	private Boolean ativo;

	@OneToMany(mappedBy = "categoria" , fetch = FetchType.EAGER)
	private List<Game> game; 
	
	
	public Categoria () {}

	public Categoria(String nomeCategoria, Boolean status) {
		this.nomeCategoria = nomeCategoria;
		this.ativo = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomeCategoria() {
		return nomeCategoria;
	}


	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Boolean getStatus() {
		return ativo;
	}


	public void setStatus(Boolean status) {
		this.ativo = status;
	}

	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Game> getGame() {
		return game;
	}

	public void setGame(List<Game> game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nomeCategoria=" + nomeCategoria + ", ativo=" + ativo + ", game=" + game + "]";
	}

	
	
	
}
