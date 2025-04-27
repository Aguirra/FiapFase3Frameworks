package br.com.fiap.calorias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.calorias.dto.ExibirUsuarioAtualizadoDTO;
import br.com.fiap.calorias.dto.ExibirUsuarioCriadoDTO;
import br.com.fiap.calorias.dto.UsuarioAtualizadoDTO;
import br.com.fiap.calorias.dto.UsuarioCriacaoDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.exception.UsuarioNaoEncontradoException;
import br.com.fiap.calorias.model.Usuarios;
import br.com.fiap.calorias.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	//Criacao de usuario
	public ExibirUsuarioCriadoDTO salvarUsuario (UsuarioCriacaoDTO usuarioDTO) { 
		
		Usuarios usuario = new Usuarios();
		BeanUtils.copyProperties(usuarioDTO, usuario);
		
		Usuarios usuarioSalvo = usuarioRepositorio.save(usuario);
		
		return new ExibirUsuarioCriadoDTO(usuarioSalvo);
		
	}
	
	/*
	 * 
	 * 	//Criacao de usuario
	public UsuarioExibicaoDTO salvarUsuario (UsuarioCriacaoDTO usuarioDTO) { 
		
		Usuarios usuario = new Usuarios();
		BeanUtils.copyProperties(usuarioDTO, usuario);
		
		Usuarios usuarioSalvo = usuarioRepositorio.save(usuario);
		
		return new UsuarioExibicaoDTO(usuarioSalvo);
		
	}
	 * */
	
	
	//buscar Usuario por ID
	public UsuarioExibicaoDTO bucarUsuarioPorId (Long id) {
		
		Optional<Usuarios> usuarioOpitional = usuarioRepositorio.findById(id);
		
		if ( usuarioOpitional.isPresent() ) {
			
			return new UsuarioExibicaoDTO(usuarioOpitional.get());
		}else {
			
			throw new UsuarioNaoEncontradoException("Usuário nao encontrado no Cadastro!");
		}
		
	}
	
	//Retornar todos os usuarios 
	public List<UsuarioExibicaoDTO> retornarTodosUsuarios () {
		return usuarioRepositorio
				.findAll()
				.stream()
				.map(UsuarioExibicaoDTO :: new)
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
	public ExibirUsuarioAtualizadoDTO atualizarUsuario ( UsuarioAtualizadoDTO usuarioDTO ) {
		
		Usuarios usuario = new Usuarios();
		BeanUtils.copyProperties(usuarioDTO, usuario);
		
		Optional<Usuarios> usuarioOptional = usuarioRepositorio.findById(usuario.getUsuarioId());
		
		if ( usuarioOptional.isPresent() ) {
			
			return new ExibirUsuarioAtualizadoDTO(usuarioRepositorio.save(usuario));
			
		}else {
			throw new RuntimeException("Usuario nao encontrado!");
		}
		
	}
	
	/*	public Usuarios atualizarUsuario ( Usuarios usuario ) {
		
		Optional<Usuarios> usuarioOptional = usuarioRepositorio.findById(usuario.getUsuarioId());
		
		if ( usuarioOptional.isPresent() ) {
			return usuarioRepositorio.save(usuario);
		}else {
			throw new RuntimeException("Usuario nao encontrado!");
		}
		
	}*/
	
}
