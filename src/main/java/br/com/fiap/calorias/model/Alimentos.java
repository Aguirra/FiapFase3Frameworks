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
@Table(name = "TBL_ALIMENTOS")
public class Alimentos {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator =  "SEQ_TBL_ALIMENTOS"
			)
	@SequenceGenerator(
			name = "SEQ_TBL_ALIMENTOS",
			sequenceName = "SEQ_TBL_ALIMENTOS",
			allocationSize = 1
			)
	@Column(name="ALIMENTO_ID")
	private Long alimentoId;
	
	
	private String nome; 
	private String porcao;
	
	@Column(name = "qtde_proteina")
	private Double quantidadeProteina;
	
	@Column(name = "qtde_carboidrato")
	private Double quantidadeCarboidrato;
	
	@Column(name = "qtde_gorduras")
	private Double quantidadeGorduras;
	
	@Column(name = "total_calorias")
	private Double totalCalorias;
	
	
	public Alimentos () {}


	public Alimentos(String nome, String porcao, Double quantidadeProteina,
			Double quantidadeCarboidrato, Double quantidadeGorduras, Double totalCalorias) {

		this.nome = nome;
		this.porcao = porcao;
		this.quantidadeProteina = quantidadeProteina;
		this.quantidadeCarboidrato = quantidadeCarboidrato;
		this.quantidadeGorduras = quantidadeGorduras;
		this.totalCalorias = totalCalorias;
	}


	public Long getAlimentoId() {
		return alimentoId;
	}


	public void setAlimentoId(Long alimentoId) {
		this.alimentoId = alimentoId;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getPorcao() {
		return porcao;
	}


	public void setPorcao(String porcao) {
		this.porcao = porcao;
	}


	public Double getQuantidadeProteina() {
		return quantidadeProteina;
	}


	public void setQuantidadeProteina(Double quantidadeProteina) {
		this.quantidadeProteina = quantidadeProteina;
	}


	public Double getQuantidadeCarboidrato() {
		return quantidadeCarboidrato;
	}


	public void setQuantidadeCarboidrato(Double quantidadeCarboidrato) {
		this.quantidadeCarboidrato = quantidadeCarboidrato;
	}


	public Double getQuantidadeGorduras() {
		return quantidadeGorduras;
	}


	public void setQuantidadeGorduras(Double quantidadeGorduras) {
		this.quantidadeGorduras = quantidadeGorduras;
	}


	public Double getTotalCalorias() {
		return totalCalorias;
	}


	public void setTotalCalorias(Double totalCalorias) {
		this.totalCalorias = totalCalorias;
	}


	@Override
	public String toString() {
		return "Alimentos [alimentoId=" + alimentoId + ", nome=" + nome + ", porcao=" + porcao + ", quantidadeProteina="
				+ quantidadeProteina + ", quantidadeCarboidrato=" + quantidadeCarboidrato + ", quantidadeGorduras="
				+ quantidadeGorduras + ", totalCalorias=" + totalCalorias + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(alimentoId, nome, porcao, quantidadeCarboidrato, quantidadeGorduras, quantidadeProteina,
				totalCalorias);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alimentos other = (Alimentos) obj;
		return Objects.equals(alimentoId, other.alimentoId) && Objects.equals(nome, other.nome)
				&& Objects.equals(porcao, other.porcao)
				&& Objects.equals(quantidadeCarboidrato, other.quantidadeCarboidrato)
				&& Objects.equals(quantidadeGorduras, other.quantidadeGorduras)
				&& Objects.equals(quantidadeProteina, other.quantidadeProteina)
				&& Objects.equals(totalCalorias, other.totalCalorias);
	} 
	
	
	
	
	
	
}
