package br.com.fiap.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.fiap.dao.CategoriaDAO;
import br.com.fiap.dao.GameDAO;
import br.com.fiap.dao.ProdutoraDAO;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Game;
import br.com.fiap.model.Produtora;
import br.com.fiap.utils.Conexao;
import jakarta.persistence.EntityManager;

public class ExecutaGameCategoria {

	public static void main(String[] args) {

		//COnexao Entity
		EntityManager em = Conexao.getEntityManager();
		
		//criando uma categoria
		//Categoria categoria = new Categoria("Aventura", true);
		Categoria categoria = new Categoria();
		categoria.setId(8);
		
		//Produtora produtora = new Produtora("Nintendo", "Japao");
		Produtora produtora = new Produtora();
		produtora.setId(1L);
		
		///Game game1 = new Game("Zelda - Ocarina of Time", LocalDate.of(1992, 8, 1), 159.98, produtora, true, categoria);
		List<Game> listarJogos = new ArrayList<Game>();
		
		//criando uma instancia de dao categoria 
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoraDAO produtoraDAO = new ProdutoraDAO(em);
		GameDAO gameDAO = new GameDAO(em);
		
		//Iniciar transacoes sobre o banco de dados
		em.getTransaction().begin();
		
		//criando a categoria 
		//categoriaDAO.salvarCategoria(categoria);
		
		//produtoraDAO.salarProdutora(produtora);
		
		//categoriaDAO.ativarInativarCategoria(categoria, false);
		//gameDAO.salvarRegistros(game1);
		
		// Efetuar o commit para sincronismo
		em.getTransaction().commit();
		
	
		listarJogos = gameDAO.listarTodosOsGames();
		
		for (Game jogo : listarJogos) {
			
			System.out.println("------------------");
			System.out.println(jogo.toString());
			
		}
		
		//Listas de Jogos por categora
		System.out.println("------------------");
		System.out.println("------------------");
		System.out.println("------------------");
		
		Categoria listarJogosPorCategoria = categoriaDAO.retornoJogosPorCategoria(categoria);
		
		System.out.println(listarJogosPorCategoria.toString());
		
		System.out.println("------------------");
		System.out.println("------------------");
		System.out.println("------------------");
		//listando por Produtora
		
		Produtora listaJogosPorProdutora = produtoraDAO.retornarJogosPorProdutora(produtora);
		System.out.println(listaJogosPorProdutora.toString());
		
		em.close();

	}

}
