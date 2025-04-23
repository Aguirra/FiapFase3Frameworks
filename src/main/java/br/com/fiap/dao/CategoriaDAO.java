package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

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
	
	public void atualizarCategoria(Categoria categoria) {
		
		this.em.merge(categoria);
		
	}
	
	public Categoria retornoJogosPorCategoria(Categoria categoria) {
		
		return this.em.find(Categoria.class, categoria.getId());

	}
	
	public void excluirRegistro(Categoria categoria) {
		Categoria excluirCategoria = this.em.find(Categoria.class, categoria);
		if (excluirCategoria != null) {
			this.em.remove(excluirCategoria);
		}
	}
	
	public Categoria obterCategoriaPorId (Categoria categoria) {
		Categoria retornoCcategoria = this.em.find(Categoria.class, categoria);
		
		if (retornoCcategoria == null) {
			return new Categoria();
		}
		return retornoCcategoria;
	}


	public List<Categoria> listarTodasAsCategorias() {
		
		String jpqlQuery = "SELECT c FROM Categoria c ORDER BY c.nomeCategoria ASC";
		
		List<Categoria> retornoCategorias = this.em.createQuery(jpqlQuery, Categoria.class).getResultList();
		
		if (retornoCategorias == null) {
			return new ArrayList<Categoria>();
		}
		return retornoCategorias;
	}
	
	
	public List<Categoria> buscarCategoriaPeloNome (String nomeCategoria) {
		String jpqlQuery = "SELECT c "
				+ "	FROM Categoria c "
				+ "  WHERE upper(c.nomeCategoria) like :nomeCategoria";
		
		List<Categoria> retornoCategorias = this.em.createQuery(jpqlQuery, Categoria.class)
				.setParameter("nomeCategoria", "%" + nomeCategoria + "%")
				.getResultList();
		
		if ( retornoCategorias.isEmpty() ) {
			return new ArrayList<Categoria>();
		}
		
		return retornoCategorias;
		
	}
	
	
	public void ativarInativarCategoria(Categoria categoria, Boolean status) {
		
		Categoria inativarCategoria = this.em.find(Categoria.class, categoria.getId());
		
		if (status) {
			if (inativarCategoria != null && !inativarCategoria.getStatus()) {
				inativarCategoria.setStatus(true);
				this.em.merge(inativarCategoria);
			}	
			
		}else {
			if (inativarCategoria != null && inativarCategoria.getStatus()) {
				inativarCategoria.setStatus(false);
				this.em.merge(inativarCategoria);
			}

		}
		
	}
	
	
	
}
