package br.com.fiap.calorias.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.ast.OpInc;
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
	//Listar Usuarios por dominios
	public List<UsuarioExibicaoDTO> retornarUariosPorDominioEmail ( String nomeDominio ) { 
		
		/*		return usuarioRepositorio.listarUsuariosPorDominio(nomeDominio)
				.stream()
				.map(UsuarioExibicaoDTO :: new) 
				.toList();*/

		List<Usuarios> retornoUsuarios = usuarioRepositorio.listarUsuariosPorDominio(nomeDominio);
		if ( retornoUsuarios.isEmpty()) {
			throw new UsuarioNaoEncontradoException("Dominio informado não localizado!");
		}
		
		return retornoUsuarios
				.stream()
				.map(UsuarioExibicaoDTO::new)
				.toList();
	}
	
	
	//buscar Usuario por ID
	public UsuarioExibicaoDTO bucarUsuarioPorId (Long id) {
		
		Optional<Usuarios> usuarioOpitional = usuarioRepositorio.findById(id);
		
		if ( usuarioOpitional.isPresent() ) {
			
			return new UsuarioExibicaoDTO(usuarioOpitional.get());
		}else {
			
			throw new UsuarioNaoEncontradoException("Usuário nao encontrado no Cadastro!");
		}
		
	}
	
	//Retornar usuarios por email 
	// cancelado para retorno de autenticação
	/*
	public UsuarioExibicaoDTO buscaUsuariosPorEmail (String email) {
		
		Optional<Usuarios> usuariosEmail = usuarioRepositorio.findByEmail(email);
		
		if ( usuariosEmail.isPresent() ) {
			return new UsuarioExibicaoDTO(usuariosEmail.get());
		}else {
			
			throw new UsuarioNaoEncontradoException("Endereço de E-MAIL não localizado.");
		}
		
	}
	*/
	
	//Retornar todos os usuarios 
	public List<UsuarioExibicaoDTO> retornarTodosUsuarios () {
		return usuarioRepositorio
				.findAll()
				.stream()
				.map(UsuarioExibicaoDTO :: new)
				.toList()
				;
	}
	
	//retorno por paginacao 
	public Page<UsuarioExibicaoDTO> listarUsuariosPorPaginas( Pageable paginacao ) {
		return usuarioRepositorio.findAll(paginacao).map(UsuarioExibicaoDTO::new);
		
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
