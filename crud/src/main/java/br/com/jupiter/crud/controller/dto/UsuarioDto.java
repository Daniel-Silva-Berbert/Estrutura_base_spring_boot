package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Usuario;

public record UsuarioDto(
  Long id,
  String userName,
  String email,
  CargoDto cargo,
  PessoaDto pessoa
) {

  public static UsuarioDto fromEntity(Usuario usuario) {
    PessoaDto pessoaDto = usuario.getPessoa() != null ?
      PessoaDto.fromEntity(usuario.getPessoa()) : null;

    CargoDto cargoDto = usuario.getCargo() != null ?
      CargoDto.fromEntity(usuario.getCargo()) : null;

    

    return new UsuarioDto(
      usuario.getId(),
      usuario.getUserName(),
      usuario.getEmail(),
      cargoDto,
      pessoaDto
    );
  }
}
