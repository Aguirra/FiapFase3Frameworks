package br.com.fiap.calorias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.calorias.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
