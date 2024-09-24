package br.com.jupiter.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jupiter.crud.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
