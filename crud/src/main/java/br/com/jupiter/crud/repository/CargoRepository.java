package br.com.jupiter.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jupiter.crud.entity.Cargo;

public interface CargoRepository extends JpaRepository <Cargo, Long>{

}
