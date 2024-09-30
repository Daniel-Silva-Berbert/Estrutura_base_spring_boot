package br.com.jupiter.crud.controller.dto;
import java.time.LocalDate;

import br.com.jupiter.crud.entity.Pessoa;

public record PessoaCreationDto(String nome, String cpf, LocalDate nascimento) {

    public Pessoa toEntity() {
    return new Pessoa(nome, cpf, nascimento);
  }
}
