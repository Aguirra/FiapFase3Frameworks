package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuarios;

public record UsuarioDTO(
		
		long usuarioId,
		String nomeUsuario,
		String email
		) {
	
	public UsuarioDTO (Usuarios usuarios) {
		
		this(
				usuarios.getUsuarioId(),
				usuarios.getNome(),
				usuarios.getEmail()
				
				);
		
		
	}
	
	

}
