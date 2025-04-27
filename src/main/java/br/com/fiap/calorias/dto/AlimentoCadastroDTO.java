package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.NotBlank;

public record AlimentoCadastroDTO(
		
        Long alimentoId,
        
        @NotBlank(message = "Informe um nome para o cadastro do produto.")
        String nome,
        String porcao,
        Double quantidadeProteina,
        Double quantidadeCarboidrato,
        Double quantidadeGorduras
		
		) {

}

