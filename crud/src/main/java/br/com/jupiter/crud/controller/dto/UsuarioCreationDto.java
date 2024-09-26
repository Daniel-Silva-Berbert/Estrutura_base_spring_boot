package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Usuario;

public record UsuarioCreationDto(String nome, String userName, String email, String password) {

  public Usuario toEntity() {
    return new Usuario(nome, userName, email, password);
  }
}
