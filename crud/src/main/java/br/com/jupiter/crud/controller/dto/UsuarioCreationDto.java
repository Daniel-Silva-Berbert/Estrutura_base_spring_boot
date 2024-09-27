package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Usuario;

import java.time.LocalDate;

public record UsuarioCreationDto(
  String nome,
  String userName,
  String email,
  String cpf,
  LocalDate nascimento,
  String password
) {

  public Usuario toEntity() {
    return new Usuario(nome, userName, email, cpf, nascimento, password);
  }
}
