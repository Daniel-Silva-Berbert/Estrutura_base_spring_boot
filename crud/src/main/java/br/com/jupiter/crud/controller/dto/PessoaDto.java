package br.com.jupiter.crud.controller.dto;

import java.time.LocalDate;

import br.com.jupiter.crud.entity.Pessoa;

public record PessoaDto(Long id, String nome, String cpf, LocalDate nascimento) {

  public static PessoaDto fromEntity(Pessoa pessoa) {
    return new PessoaDto(
      pessoa.getId(),
      pessoa.getNome(),
      pessoa.getCpf(),
      pessoa.getNascimento()
    );
  }
}
