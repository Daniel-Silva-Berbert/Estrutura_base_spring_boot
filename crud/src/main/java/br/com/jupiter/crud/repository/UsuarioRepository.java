package br.com.jupiter.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jupiter.crud.entity.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
  List<Usuario> findByNomeContainingIgnoreCase(String nome);

}
