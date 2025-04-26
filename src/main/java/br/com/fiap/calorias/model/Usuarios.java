package br.com.fiap.calorias.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_USUARIOS")
public class Usuarios {
	
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



	

}
