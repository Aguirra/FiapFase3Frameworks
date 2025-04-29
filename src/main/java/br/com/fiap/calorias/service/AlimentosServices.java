package br.com.fiap.calorias.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.calorias.dto.AlimentoCadastroDTO;
import br.com.fiap.calorias.dto.AlimentoExibicaoDTO;
import br.com.fiap.calorias.exception.UsuarioNaoEncontradoException;
import br.com.fiap.calorias.model.Alimentos;
import br.com.fiap.calorias.repository.AlimentosRepository;

@Service
public class AlimentosServices {
	
	@Autowired
	private AlimentosRepository alimentoRepository;

    public Double calcularCalorias(Double proteinas, Double carboidratos, Double gorduras){
        Double calorias = (proteinas * 4) + (carboidratos * 4) + (gorduras * 9);
        return calorias;
    }
	
	
    public AlimentoExibicaoDTO salvarAlimento(AlimentoCadastroDTO alimentoDTO){

        Alimentos alimento = new Alimentos();
        BeanUtils.copyProperties(alimentoDTO, alimento);
        
        alimento.setTotalCalorias(
                calcularCalorias(
                    alimento.getQuantidadeProteina(),
                    alimento.getQuantidadeCarboidrato(),
                    alimento.getQuantidadeGorduras()
                )
        );

        Alimentos alimentoSalvo = alimentoRepository.save(alimento);
        return new AlimentoExibicaoDTO(alimentoSalvo);

    }

    public AlimentoExibicaoDTO buscarPorId(Long id){
        Optional<Alimentos> alimentoOptional =
                alimentoRepository.findById(id);

        if (alimentoOptional.isPresent()){
            return new AlimentoExibicaoDTO(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não existe!");
        }
    }
    
    //Buscar Alimento por Nome 
    public AlimentoExibicaoDTO buscarAlimentoPorNome( String nomeAlimento) {
    	
    	Optional<Alimentos> retornoAlimentoOptional = alimentoRepository.buscarAlimentoPorNome(nomeAlimento);
    	
    	if ( retornoAlimentoOptional.isPresent() ) {
    		
    		return new AlimentoExibicaoDTO(retornoAlimentoOptional.get());
    		
    	} else {
    		System.out.println("excecao ");
    		throw new UsuarioNaoEncontradoException("Alimento nao localizado pelo nome.");
    	}
    	
    }
    
    //pesquisar por nomes
    public List<AlimentoExibicaoDTO> listarAlimentosPorNome (String nome) {
    	
    	//List<Alimentos> retornoAlimentos = alimentoRepository.pesquisarAlimentosPorNomes(nome);

    	return alimentoRepository.pesquisarAlimentosPorNomes(nome.toUpperCase())
    			.stream()
    			.map(AlimentoExibicaoDTO :: new)
    			.toList();
    }
    
    //Listar itens menor que um valor 
    public List<AlimentoExibicaoDTO> listarAlimentosGprdurasMenor( Double quantidade) {
    	
    	return alimentoRepository.listarAlimentosInferiores(quantidade)
    			.stream()
    			.map(AlimentoExibicaoDTO :: new) 
    			.toList();
    }
    
    
    //Buscar por faixa de total de caloria 
    public List<AlimentoExibicaoDTO> buscarPorFaixaCaloria (Double minimoValor, Double maximoValor) {
    	
    	return alimentoRepository.buscarAlimentosPorFaixaDeCalorias(minimoValor, maximoValor)
    			.stream()
    			.map(AlimentoExibicaoDTO :: new)
    			.toList();
    }
    
    //menor que total calorias
    public List<AlimentoExibicaoDTO> listarTotalCaloriasMenorQue(Double totalCalorias) {
    	
    	return alimentoRepository.findByTotalCaloriasLessThan(totalCalorias);
    	
    }
    
    
    public List<AlimentoExibicaoDTO> listarTodos(){
        return alimentoRepository
                .findAll()
                .stream()
                .map(AlimentoExibicaoDTO::new)
                .toList();
    }
    
    
    public void excluir(Long id){
        Optional<Alimentos> alimentoOptional =
                alimentoRepository.findById(id);

        if (alimentoOptional.isPresent()){
            alimentoRepository.delete(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }

    public AlimentoExibicaoDTO atualizar(AlimentoCadastroDTO alimentoDTO){
        Optional<Alimentos> alimentoOptional =
                alimentoRepository.findById(alimentoDTO.alimentoId());

        if (alimentoOptional.isPresent()){
            Alimentos alimento = new Alimentos();
            BeanUtils.copyProperties(alimentoDTO, alimento);

            alimento.setTotalCalorias(
                    calcularCalorias(
                            alimento.getQuantidadeProteina(),
                            alimento.getQuantidadeCarboidrato(),
                            alimento.getQuantidadeGorduras()
                    )
            );
            
            return new AlimentoExibicaoDTO(alimentoRepository.save(alimento));
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }

}
