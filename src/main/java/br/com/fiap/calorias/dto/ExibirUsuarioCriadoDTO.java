package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuarios;

public record ExibirUsuarioCriadoDTO(
		
		Long usuarioId,
		String nome,
		String statusCriacao
		
		) {
	
	
	public ExibirUsuarioCriadoDTO(Usuarios usuarios) {
		this (
				usuarios.getUsuarioId(),
				usuarios.getNome(),
				"Usuário criado com sucesso."
				
				);
	}

}
