package br.com.jupiter.crud.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.jupiter.crud.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);
}
