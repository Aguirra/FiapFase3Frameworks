package br.com.fiap.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dao.CategoriaDAO;
import br.com.fiap.dao.GameDAO;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Game;
import br.com.fiap.utils.Conexao;
import jakarta.persistence.EntityManager;

public class ExecutaGameCategoria {

	public static void main(String[] args) {

		//COnexao Entity
		EntityManager em = Conexao.getEntityManager();
		
		//criando uma categoria
		//Categoria categoria = new Categoria();
		//categoria.setId(3);
		
		//Game game1 = new Game("Mario Brother`s", LocalDate.of(1992, 8, 1), 350.98, "Nintendo", true, categoria);
		List<Game> listarJogos = new ArrayList<Game>();
		
		//criando uma instancia de dao categoria 
		//CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		GameDAO gameDAO = new GameDAO(em);
		
		//Iniciar transacoes sobre o banco de dados
		em.getTransaction().begin();
		
		//criando a categoria 
		//categoriaDAO.salvarCategoria(categoria);
		//gameDAO.salvarRegistros(game1);
		
		// Efetuar o commit para sincronismo
		em.getTransaction().commit();
		
		
		listarJogos = gameDAO.listarTodosOsGames();
		
		for (Game jogo : listarJogos) {
			
			System.out.println("------------------");
			System.out.println(jogo.toString());
			
		}
		

		
		em.close();
		
		
		

	}

}
