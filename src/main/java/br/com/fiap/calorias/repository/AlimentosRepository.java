package br.com.fiap.calorias.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.calorias.dto.AlimentoExibicaoDTO;
import br.com.fiap.calorias.model.Alimentos;

public interface AlimentosRepository extends JpaRepository<Alimentos, Long>{
	
	@Query("SELECT a FROM Alimentos a WHERE a.nome = :nome")
	Optional<Alimentos> buscarAlimentoPorNome (@Param("nome") String nome);

	@Query("SELECT a FROM Alimentos a WHERE a.totalCalorias between :minimoValor and :maximoValor")
	List<Alimentos> buscarAlimentosPorFaixaDeCalorias (@Param("minimoValor") Double minimoValor, @Param("maximoValor") Double maximoValor );
	
	
	//Pesquisa por nome 
	@Query("SELECT a FROM Alimentos a WHERE a.nome LIKE CONCAT( '%', :nome, '%' )  ")
	List<Alimentos> pesquisarAlimentosPorNomes(@Param("nome") String nome);
	
	
	//Pesquisa sobre retornar itens de alimentos menor que uma quantidade determinada 
	@Query("SELECT a FROM Alimentos a WHERE a.quantidadeGorduras <= :valorMaximo")
	List<Alimentos> listarAlimentosInferiores (@Param("valorMaximo") Double valorMaximo);
	
	//busca por totalCaloria lessTahn (menor que o valor informado)
	List<AlimentoExibicaoDTO> findByTotalCaloriasLessThan(Double totalCalorias);

}
