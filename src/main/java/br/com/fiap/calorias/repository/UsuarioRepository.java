package br.com.fiap.calorias.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.fiap.calorias.model.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{
	
	
	//Listar emails por dominio
	@Query("SELECT u FROM Usuarios u WHERE SUBSTR(u.email, INSTR(u.email, '@') + 1) = :dominioEmail ")
	List<Usuarios> listarUsuariosPorDominio (@Param("dominioEmail") String dominioEmail) ;
	//Optional<Usuarios> listarUsuariosPorDominio (@Param("dominioEmail") String dominioEmail) ;
	
	//cancelado o Option devido a autenticação
	//Optional<Usuarios> findByEmail (String email);
	
	UserDetails findByEmail(String email);

}
