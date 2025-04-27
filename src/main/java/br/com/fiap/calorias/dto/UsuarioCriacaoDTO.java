package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCriacaoDTO(
		
		Long usuarioId,
		
		@NotBlank(message = "O nome do usuário é Obrigatório!")
		String nome,
		
		@NotBlank(message = "O E-mail do usuário é Obrigatório.")
		@Email(message = "E-mail informado inválido.")
		String email,
		
		@NotBlank(message = "A senha para o usuário é Obrigatória.")
		@Size(min = 6, max = 20, message = "A senha deve conter entre 6 a 20 caracteres!")
		String senha
		) {
	


}
