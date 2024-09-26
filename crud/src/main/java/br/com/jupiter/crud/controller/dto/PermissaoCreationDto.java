package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Permissao;

public record PermissaoCreationDto(String nome) {

  public Permissao toEntity() {
    return new Permissao(nome);
  }
}
