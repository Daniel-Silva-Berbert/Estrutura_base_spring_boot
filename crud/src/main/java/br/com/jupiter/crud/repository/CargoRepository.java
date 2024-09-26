package br.com.jupiter.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jupiter.crud.entity.Cargo;

import java.util.List;

public interface CargoRepository extends JpaRepository <Cargo, Long>{
  List<Cargo> findByNomeContainingIgnoreCase(String nome);
}
