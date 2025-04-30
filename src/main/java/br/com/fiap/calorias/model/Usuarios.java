package br.com.fiap.calorias.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_USUARIOS")
public class Usuarios implements UserDetails {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =  "SEQ_TBL_USUARIOS"
			)
	@SequenceGenerator(
			name = "SEQ_TBL_USUARIOS",
			sequenceName = "SEQ_TBL_USUARIOS",
			allocationSize = 1
			)
	@Column(name="usuario_id")
	private Long usuarioId;
	
	private String nome;
	private String email;
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private UsuarioRole role;
	
	public Usuarios () {} 
	
	
	public Usuarios(String nome, String email, String senha) {

		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	



	public UsuarioRole getRole() {
		return role;
	}


	public void setRole(UsuarioRole role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, nome, senha, usuarioId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		return Objects.equals(email, other.email) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha) && Objects.equals(usuarioId, other.usuarioId);
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		if ( this.role == UsuarioRole.ADMIN ) {
			
			return List.of(
					new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_USER")
					);	
		}else {
			
			return List.of (
					new SimpleGrantedAuthority("ROLE_USER")
					);
		}
		
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}



	

}
