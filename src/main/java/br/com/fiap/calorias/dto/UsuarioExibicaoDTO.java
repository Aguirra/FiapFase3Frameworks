package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuarios;

public record UsuarioExibicaoDTO(
		
		long codigo,
		String nome,
		String email
		) {
	
	public UsuarioExibicaoDTO (Usuarios usuarios) {
		
		this(
				usuarios.getUsuarioId(),
				usuarios.getNome(),
				usuarios.getEmail()
				
				);
		
		
	}
	
	

}
