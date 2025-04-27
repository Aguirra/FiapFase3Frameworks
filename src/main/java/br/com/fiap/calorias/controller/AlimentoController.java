package br.com.fiap.calorias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    //Listar Alimentos por valor total de calorias
    @RequestMapping(value = "/alimentos", params = {"caloriasMinima", "caloriasMaxima"} )
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> listarAlimentosPorCalorias (
    		@RequestParam("caloriasMinima") Double valorMinimo, 
    		@RequestParam("caloriasMaxima") Double maximoValor) { 
    	
    	
    	return alimentoService.buscarPorFaixaCaloria(valorMinimo, maximoValor);
    	
    	
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
