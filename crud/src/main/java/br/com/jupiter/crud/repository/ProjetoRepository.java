package br.com.jupiter.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jupiter.crud.entity.Projeto;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
  List<Projeto> findByNomeContainingIgnoreCase(String nome);
}
