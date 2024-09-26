package br.com.jupiter.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jupiter.crud.entity.Permissao;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
  List<Permissao> findByNomeContainingIgnoreCase(String nome);
}
