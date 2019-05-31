package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.CategoriaModel;

// Interface padrão Repository, deve ser criada para utilizarmos os métodos do Hibernate 
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
	
}
