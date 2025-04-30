package br.com.fiap.calorias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.com.fiap.calorias.dto.AlimentoCadastroDTO;
import br.com.fiap.calorias.dto.AlimentoExibicaoDTO;
import br.com.fiap.calorias.service.AlimentosServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AlimentoController {
    @Autowired
    private AlimentosServices alimentoService;

    @PostMapping("/alimentos")
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentoExibicaoDTO salvar(
            @RequestBody @Valid AlimentoCadastroDTO alimento){
        return alimentoService.salvarAlimento(alimento);
    }

    @GetMapping("/alimentos")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> litarTodos(){
        return alimentoService.listarTodos();
    }
    
    //listar todos Paginado
    
    @RequestMapping(value = "/alimentos" , params = "paginacao")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlimentoExibicaoDTO> listarTodosPaginado ( 
    		@PageableDefault(size = 2, page = 0)
    		Pageable paginacao ) {
    	
    	return alimentoService.listarTodosPorPaginacao(paginacao);
    }

    @GetMapping("/alimentos/{alimentoId}")
    public ResponseEntity<AlimentoExibicaoDTO> buscarPorId(
            @PathVariable Long alimentoId){
        try {
            return ResponseEntity
                    .ok(alimentoService.buscarPorId(alimentoId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
   
    //Buscar Alimento pelo nome 
    @RequestMapping(value = "/alimentos", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AlimentoExibicaoDTO> buscarAlimentoPorNome ( @RequestParam String nome){

    	try {
            return ResponseEntity
                    .ok(alimentoService.buscarAlimentoPorNome(nome.toUpperCase()));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    	
    }
    
    //Listar nomes de Alimentos Like 
    @RequestMapping(value = "/alimentos", params = "nomeAlimento")
    public List<AlimentoExibicaoDTO> listarAlimentos (@RequestParam String nomeAlimento) {
    	
    	return alimentoService.listarAlimentosPorNome(nomeAlimento);
    }
    
    
    //Listar Gorduras menores
    @RequestMapping(value = "/alimentos", params = "gorduraMaxima") 
    public List<AlimentoExibicaoDTO> listarGordurasMenores (@RequestParam("gorduraMaxima") Double gorduraMaxima) {
    	
    	return alimentoService.listarAlimentosGprdurasMenor(gorduraMaxima);
    }
    
    
    
    //Listar Alimentos por valor total de calorias
    @RequestMapping(value = "/alimentos", params = {"caloriasMinima", "caloriasMaxima"} )
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> listarAlimentosPorCalorias (
    		@RequestParam("caloriasMinima") Double valorMinimo, 
    		@RequestParam("caloriasMaxima") Double maximoValor) { 
    	
    	
    	return alimentoService.buscarPorFaixaCaloria(valorMinimo, maximoValor);
    	
    	
    }
    
    // Listar calorias menor que 
    @RequestMapping(value="/alimentos" , params = "caloriasMenorQue")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> listarCaloriasMenorQue ( @RequestParam Double caloriasMenorQue) {
    	
    	return alimentoService.listarTotalCaloriasMenorQue(caloriasMenorQue);
    }

    @DeleteMapping("/alimentos/{alimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long alimentoId){
        alimentoService.excluir(alimentoId);
    }

    @PutMapping("/alimentos")
    public ResponseEntity<AlimentoExibicaoDTO> atualizar(
            @RequestBody AlimentoCadastroDTO alimentoDTO){
        try {
            AlimentoExibicaoDTO alimentoExibicaoDTO =
                    alimentoService.atualizar(alimentoDTO);
            return ResponseEntity.ok(alimentoExibicaoDTO);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
