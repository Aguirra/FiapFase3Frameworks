package br.com.fiap.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dao.GameDAO;
import br.com.fiap.model.Game;
import br.com.fiap.utils.Conexao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ExecutarGames {

	
	public static void main (String[] args) {
		
		System.out.println("Iniciando processo FIAP");
		Game game = new Game();
		
		game.setId(3L);
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("games");
		//EntityManager em = emf.createEntityManager();
		
		EntityManager em = Conexao.getEntityManager();
		GameDAO daoGames = new GameDAO(em);
		
		em.getTransaction().begin();
		
		//daoGames.salvarRegistros(game1);
		//daoGames.atualizarRegistros(game1);
		//daoGames.exclusaoRegistro(game1);
		
		Game retornoJogo = daoGames.buscarGamePeloId(game);
		
		if ( retornoJogo != null ) {
			System.out.println("Jogo Encontrado!");
			System.out.println("Titulo: " + retornoJogo.getTitulo());
			System.out.println("Categoria: " + retornoJogo.getCategoria());
			System.out.println("Produtora: " + retornoJogo.getProdutora());
			System.out.println("Data Lançamento: " + retornoJogo.getDataLancamento());
			System.out.println("Valor: " + retornoJogo.getValor());
			
			System.out.println(retornoJogo.toString());
		}else {
			System.out.println("Jogo não encontrado.");
		}
		
		
		List<Game> listaDeJogos = new ArrayList<Game>();
		List<Game> buscarJogo = new ArrayList<Game>();
		List<Game> buscarJogoPorValor = new ArrayList<Game>();
		
		listaDeJogos = daoGames.listarTodosOsGames();
		System.out.println("Lista de Games.");
		for (Game game2 : listaDeJogos) {
			System.out.println(game2.toString());
		}
		
		System.out.println("----");
		System.out.println("Retorno de busca por titulo");
		buscarJogo = daoGames.buscarGamePeloNome("mario");
		
		if (! buscarJogo.isEmpty() ) {
			System.out.println(buscarJogo.toString());
		}
		
		//buscarJogoPorValor
		System.out.println("----");
		System.out.println("Retorno de busca por Valores");
		buscarJogoPorValor = daoGames.buscarJogosPorValores(100.00, 299.99);
		
		if (! buscarJogoPorValor.isEmpty() ) {
			
			for (Game jogos : buscarJogoPorValor) {
				System.out.println(jogos.toString());
			}
			
		}
		
		
		em.getTransaction().commit();
		em.close();
		
	}
}
