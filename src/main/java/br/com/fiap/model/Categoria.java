package br.com.fiap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private int id;
	
	private String nomeCategoria;
	
	
	public Categoria () {}


	public Categoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
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


	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nomeCategoria=" + nomeCategoria + "]";
	} 
	
	
	
	
}
