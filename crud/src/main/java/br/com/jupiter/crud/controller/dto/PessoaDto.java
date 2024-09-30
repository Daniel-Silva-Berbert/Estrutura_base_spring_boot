package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Pessoa;

public record PessoaDto(Long id, String nome) {

  public static PessoaDto fromEntity(Pessoa pessoa) {
    return new PessoaDto(
      pessoa.getId(),
      pessoa.getNome()
    );
  }
}
