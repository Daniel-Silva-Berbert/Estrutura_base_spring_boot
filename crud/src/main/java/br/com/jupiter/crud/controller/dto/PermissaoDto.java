package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Permissao;
import br.com.jupiter.crud.entity.Usuario;

import java.util.List;

public record PermissaoDto(Long id, String nome) {

  // public static PermissaoDto fromEntity(Permissao permissao) {
  //   return new PermissaoDto(
  //     permissao.getId(),
  //     permissao.getNome()
  //   );
  // }
}
