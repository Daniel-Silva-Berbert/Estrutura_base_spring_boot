package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Projeto;

public record ProjetoCreationDto(String nome) {
  public Projeto toEntity() {
    return new Projeto(nome);
  }
}
