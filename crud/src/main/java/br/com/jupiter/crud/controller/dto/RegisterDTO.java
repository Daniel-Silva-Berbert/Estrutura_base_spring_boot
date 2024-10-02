package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Permissao;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
    @NotBlank String userName,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 6) String password,
    @NotNull Permissao permissao,
    @Nullable Long pessoaId,
    @Nullable Long cargoId) { }
