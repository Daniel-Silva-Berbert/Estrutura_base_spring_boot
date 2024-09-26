package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Cargo;

public record CargoCreationDto(String nome, Float remuneracao) {
  public Cargo toEntity() {
    return new Cargo(nome, remuneracao);
  }
}
