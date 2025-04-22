package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Game;
import jakarta.persistence.EntityManager;

public class GameDAO {
	
	private EntityManager em;
	
	public GameDAO(EntityManager em) {
		this.em = em;
	}

	public void salvarRegistros (Game game) {
		this.em.persist(game);
	}
	
	//atualização 
	public void atualizarRegistros(Game game) {
		this.em.merge(game);
	}
	
	//excluir registros 
	public void exclusaoRegistro (Game game) {
		Game gameExcluir = this.em.find(Game.class, game.getId());
		
		if (gameExcluir != null) {
			this.em.remove(gameExcluir);
		}
		
	}
	
	//buscar pelo ID 
	public Game buscarGamePeloId(Game game) {
		
		Game retornoId = this.em.find(Game.class, game.getId());
		
		if (retornoId == null) {
			return new Game();
		}
		return retornoId;
		
	}
	
	//buscar todos os Games
	public List<Game> listarTodosOsGames() {
		
		String jpqlQuery = "SELECT g FROM Game g ORDER BY g.titulo ASC";
		
		List<Game> retornoListaGames = this.em.createQuery(jpqlQuery, Game.class).getResultList();
		
		if (retornoListaGames.isEmpty() ) {
			return new ArrayList<Game>();
		}
		return retornoListaGames;
		
	}
	
	//Buscar por titulo 
	public List<Game> buscarGamePeloNome (String titulo) {
		
		String jpqlQuery = "SELECT g FROM Game g WHERE upper(g.titulo) like :titulo ";
		
		List<Game> retornoJogo = this.em.createQuery(jpqlQuery, Game.class)
				.setParameter("titulo", "%" + titulo.toUpperCase() + "%")
				.getResultList();
		System.out.println(titulo.toUpperCase());
		
		if (retornoJogo.isEmpty() ) {
			return new ArrayList<Game>();
		}
		return retornoJogo;
	}
	
	
	//Buscar por valores 
	public List<Game> buscarJogosPorValores(Double valorInicial, Double valorFinal) {
		
String jpqlQuery = "SELECT g FROM Game g WHERE g.valor BETWEEN :valorInicial AND :valorFinal"
		+ " ORDER BY g.titulo ASC";
		
		List<Game> retornoJogo = this.em.createQuery(jpqlQuery, Game.class)
				.setParameter("valorInicial", valorInicial)
				.setParameter("valorFinal", valorFinal)
				.getResultList();
				
		if (retornoJogo.isEmpty() ) {
			return new ArrayList<Game>();
		}
		return retornoJogo;
	}
}
