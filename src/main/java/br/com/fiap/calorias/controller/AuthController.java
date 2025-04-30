package br.com.fiap.calorias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.fiap.calorias.dto.ExibirUsuarioCriadoDTO;
import br.com.fiap.calorias.dto.UsuarioCriacaoDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.service.UsuarioServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager autenthicateManager;
	
	@Autowired
	private UsuarioServices usuarioServico;
	
	public ResponseEntity login (
			
			@RequestBody
			@Valid
			UsuarioCriacaoDTO usuariosCadDto
			
			) {
		UsernamePasswordAuthenticationToken userNamePassword = 
				new UsernamePasswordAuthenticationToken(
						
						usuariosCadDto.email(),
						usuariosCadDto.senha()
						
						);
		
		Authentication auth = autenthicateManager.authenticate(userNamePassword);
		
		return ResponseEntity.ok().build();

	}
	
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity criarUsuario (
			@RequestBody
			@Valid
			UsuarioCriacaoDTO usuarioCriaDTO
			) {
		
		ExibirUsuarioCriadoDTO usuarioSalvo = null;
		usuarioSalvo = usuarioServico.salvarUsuario(usuarioCriaDTO);
		
		return ResponseEntity.ok(usuarioSalvo);
	}
	
}
