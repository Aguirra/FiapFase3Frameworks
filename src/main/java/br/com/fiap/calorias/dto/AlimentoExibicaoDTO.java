package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Alimentos;

public record AlimentoExibicaoDTO(
		
		Long alimentoId,
		String nome,
		String porcao,
		Double quantidadeProteirna,
		Double quantidadeCarboidrato,
		Double quantidadeGorduras,
		Double totalCalorias
		
		) {
	
	
	public AlimentoExibicaoDTO(Alimentos alimentos) {
		
		this (
				alimentos.getAlimentoId(),
				alimentos.getNome(),
				alimentos.getPorcao(),
				alimentos.getQuantidadeProteina(),
				alimentos.getQuantidadeCarboidrato(),
				alimentos.getQuantidadeGorduras(),
				alimentos.getTotalCalorias()
				
				); 
		
	}

	
	
}
