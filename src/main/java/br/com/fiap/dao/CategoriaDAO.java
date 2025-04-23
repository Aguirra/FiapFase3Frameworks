package br.com.fiap.dao;

import br.com.fiap.model.Categoria;
import jakarta.persistence.EntityManager;





public class CategoriaDAO {

	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	
	public void salvarCategoria(Categoria categoria) {
		
		this.em.persist(categoria);
		
	}
	
	

	
	
}
