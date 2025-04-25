package br.com.fiap.calorias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.service.UsuarioServices;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private UsuarioServices usuarioService;
	
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvarUsuario (@RequestBody Usuario usuario) {
		
		return usuarioService.salvarUsuario(usuario);
		
	}
	
	@GetMapping("/usuarios")
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> buscarTodosUsuario () {
		return usuarioService.retornarTodosUsuarios();
	}
	
	
	@GetMapping("/usuarios/{usuarioId}")
	public Usuario buscarUsuarioPorId ( @PathVariable Long usuarioId) { 
		return usuarioService.bucarUsuarioPorId(usuarioId);
	}
	
	
	@DeleteMapping("/usuarios/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirUsuario ( @PathVariable Long usuarioId) {
		usuarioService.excluirUsuario(usuarioId);
	}
	
	
	@PutMapping("/usuarios")
	@ResponseStatus(HttpStatus.OK)
	public Usuario atualizarUsuario ( @RequestBody Usuario usuario) {
		
		return usuarioService.atualizarUsuario(usuario);
		
	}
	
	
	
}
