package br.com.fiap.model;

import java.time.LocalDate;

import org.hibernate.annotations.DialectOverride.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_games")
public class Game {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "TBL_GAMES_SEQ")
	@SequenceGenerator(
			name = "TBL_GAMES_SEQ",
			sequenceName = "TBL_GAMES_SEQ",
			allocationSize = 1)
	private Long id;
	private String titulo;
	
	@Column(name= "data_lancamento")
	private LocalDate dataLancamento;
	private Double valor;
	private String produtora;
	private Boolean finalizado;
	//private String categoria;
	
	@ManyToOne
	
	private Categoria categoria;
	
	public Game () {} 
	
	public Game(String titulo, LocalDate dataLancamento, Double valor, String produtora, Boolean finalizado,
			Categoria categoria) {
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.valor = valor;
		this.produtora = produtora;
		this.finalizado = finalizado;
		this.categoria = categoria;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getProdutora() {
		return produtora;
	}
	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}
	public Boolean getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	  @Override
	    public String toString() {
	        return "ID:" + this.id + ""
	                + "\nTITULO: " + this.titulo + ""
	                + "\nPRODUTORA: " + this.produtora + ""
	                + "\nCATEGORIA: " + this.categoria.getNomeCategoria() + ""
	                + "\nLANÇAMENTO: " + this.dataLancamento + ""
	                + "\nFINALIZADO: " + this.finalizado + ""
	                + "\nVALOR: " + this.valor;
	    }
	
	
}
