package br.com.fiap.calorias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.calorias.model.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{

}
