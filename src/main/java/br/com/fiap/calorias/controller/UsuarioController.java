package br.com.fiap.calorias.controller;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.calorias.dto.ExibirUsuarioAtualizadoDTO;
import br.com.fiap.calorias.dto.ExibirUsuarioCriadoDTO;
import br.com.fiap.calorias.dto.UsuarioAtualizadoDTO;
import br.com.fiap.calorias.dto.UsuarioCriacaoDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.service.UsuarioServices;
import jakarta.validation.Valid;


@RestController 
@RequestMapping("/api")
public class UsuarioController {

	
	@Autowired
	private UsuarioServices usuarioService;
	
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public ExibirUsuarioCriadoDTO salvarUsuario (@RequestBody @Valid UsuarioCriacaoDTO usuarioDTO) {
		
		return usuarioService.salvarUsuario(usuarioDTO);
		
	}
	
	@GetMapping("/usuarios")
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioExibicaoDTO> buscarTodosUsuario () {
		return usuarioService.retornarTodosUsuarios();
	}
	
	
	/*	@GetMapping("/usuarios/{usuarioId}")
	public UsuarioExibicaoDTO buscarUsuarioPorId ( @PathVariable Long usuarioId) { 
		return usuarioService.bucarUsuarioPorId(usuarioId);
	}*/
	
	@GetMapping("/usuarios/{usuarioId}")
	public ResponseEntity<UsuarioExibicaoDTO> buscarUsuarioPorId ( @PathVariable Long usuarioId) { 
		
		
		return ResponseEntity.ok(usuarioService.bucarUsuarioPorId(usuarioId));
	}
	
	//retornar usuarios por dominio de email 
	@RequestMapping(value = "/usuarios" , params = "dominioEmail")
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioExibicaoDTO> listarUsuariosPorDominio(@Param("dominioEmail") String dominioEmail) {
		
		return usuarioService.retornarUariosPorDominioEmail(dominioEmail);

	}
	
	//Retornar Usuarios por e-mail
	@RequestMapping(value = "/usuarios", params = "email")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioExibicaoDTO retornoUsuariosPorEmail (@Param("email") String email) {
		
		return usuarioService.buscaUsuariosPorEmail(email);
		
	}
	
	
	
	@DeleteMapping("/usuarios/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirUsuario ( @PathVariable Long usuarioId) {
		usuarioService.excluirUsuario(usuarioId);
	}
	
	
	@PutMapping("/usuarios")
	@ResponseStatus(HttpStatus.OK)
	public ExibirUsuarioAtualizadoDTO atualizarUsuario ( @RequestBody UsuarioAtualizadoDTO usuario) {
		System.out.println(usuario);
		return usuarioService.atualizarUsuario(usuario);
		
	}
	
}
