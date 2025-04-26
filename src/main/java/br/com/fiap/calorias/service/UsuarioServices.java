package br.com.fiap.calorias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.calorias.dto.UsuarioDTO;
import br.com.fiap.calorias.model.Usuarios;
import br.com.fiap.calorias.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	//Criacao de usuario
	public Usuarios salvarUsuario (Usuarios usuario) { 
		
		return usuarioRepositorio.save(usuario);
		
	}
	
	
	//buscar Usuario por ID
	public UsuarioDTO bucarUsuarioPorId (Long id) {
		
		Optional<Usuarios> usuarioOpitional = usuarioRepositorio.findById(id);
		
		if ( usuarioOpitional.isPresent() ) {
			
			return new UsuarioDTO(usuarioOpitional.get());
		}else {
			
			throw new RuntimeException("Usuario nao Existe!");
		}
		
	}
	
	//Retornar todos os usuarios 
	public List<UsuarioDTO> retornarTodosUsuarios () {
		return usuarioRepositorio
				.findAll()
				.stream()
				.map(UsuarioDTO :: new)
				.toList()
				;
	}
	
	//Excluir registro
	public void excluirUsuario (Long id) {
		
		Optional<Usuarios> usuarioOpition = usuarioRepositorio.findById(id);
		
		if ( usuarioOpition.isPresent() ) {
			usuarioRepositorio.delete(usuarioOpition.get());
		}else {
			throw new RuntimeException("Usuario nao encontrado!");
		}
		
	}
	
	
	//Atualizacao de registro
	public Usuarios atualizarUsuario ( Usuarios usuario ) {
		
		Optional<Usuarios> usuarioOptional = usuarioRepositorio.findById(usuario.getUsuarioId());
		
		if ( usuarioOptional.isPresent() ) {
			return usuarioRepositorio.save(usuario);
		}else {
			throw new RuntimeException("Usuario nao encontrado!");
		}
		
	}
	
}
