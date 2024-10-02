package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Permissao;

public record AuthDto(String userName, String email, String password, Permissao permissao) {
}
