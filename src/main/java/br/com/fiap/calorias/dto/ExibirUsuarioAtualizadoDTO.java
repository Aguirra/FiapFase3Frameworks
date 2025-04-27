package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuarios;

public record ExibirUsuarioAtualizadoDTO(
		
		Long usuarioId,
		String statusAtualizaco
		
		
		) {
	
	 public ExibirUsuarioAtualizadoDTO(Usuarios usuarios ) {
		 
		 
		 this (
				 usuarios.getUsuarioId(),
				 "Usuário atualizado com sucesso."
				 ) ;
	}

}
