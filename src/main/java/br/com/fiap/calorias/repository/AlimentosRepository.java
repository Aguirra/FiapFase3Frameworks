package br.com.fiap.calorias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.calorias.model.Alimentos;

public interface AlimentosRepository extends JpaRepository<Alimentos, Long>{

}
