package br.com.jupiter.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jupiter.crud.entity.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{

}
