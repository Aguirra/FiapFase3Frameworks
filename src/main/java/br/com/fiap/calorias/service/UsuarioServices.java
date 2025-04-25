package br.com.fiap.calorias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.repository.UsuarioRepository;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	//Criacao de usuario
	public Usuario salvarUsuario (Usuario usuario) { 
		
		return usuarioRepositorio.save(usuario);
		
	}
	
	
	//buscar Usuario por ID
	public Usuario bucarUsuarioPorId (Long id) {
		
		Optional<Usuario> usuarioOpitional = usuarioRepositorio.findById(id);
		
		if ( usuarioOpitional.isPresent() ) {
			return usuarioOpitional.get();
		}else {
			
			throw new RuntimeException("Usuario nao Existe!");
		}
		
	}
	
	//Retornar todos os usuarios 
	public List<Usuario> retornarTodosUsuarios () {
		return usuarioRepositorio.findAll();
	}
	
	//Excluir registro
	public void excluirUsuario (Long id) {
		
		Optional<Usuario> usuarioOpition = usuarioRepositorio.findById(id);
		
		if ( usuarioOpition.isPresent() ) {
			usuarioRepositorio.delete(usuarioOpition.get());
		}else {
			throw new RuntimeException("Usuario nao encontrado!");
		}
		
	}
	
	
	//Atualizacao de registro
	public Usuario atualizarUsuario ( Usuario usuario ) {
		
		Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(usuario.getUsuarioId());
		
		if ( usuarioOptional.isPresent() ) {
			return usuarioRepositorio.save(usuario);
		}else {
			throw new RuntimeException("Usuario nao encontrado!");
		}
		
	}
	
	
	
	
	
	
}
