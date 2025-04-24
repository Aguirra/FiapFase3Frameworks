package br.com.fiap.dao;

import br.com.fiap.model.Produtora;
import jakarta.persistence.EntityManager;

public class ProdutoraDAO {
	
	private EntityManager em;
	

	public ProdutoraDAO (EntityManager em) {
		
		this.em = em;
	} 
	
	
	public void salarProdutora (Produtora produtora) {
		
		this.em.persist(produtora);
		
	} 
	
	
	public Produtora retornarJogosPorProdutora(Produtora produtora) {
		
		return this.em.find(Produtora.class, produtora.getId());
	}

}
