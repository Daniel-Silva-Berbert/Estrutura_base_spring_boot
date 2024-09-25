package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Cargo;

public record CargoDto(Long id, String nome, Float remuneracao) {

  public static CargoDto fromEntity(Cargo cargo) {
    return new CargoDto(
      cargo.getId(),
      cargo.getNome(),
      cargo.getRemuneracao()
    );
  }
}
