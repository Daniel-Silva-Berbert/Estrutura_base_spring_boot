package br.com.jupiter.crud.controller;

import br.com.jupiter.crud.controller.dto.PermissaoCreationDto;
import br.com.jupiter.crud.controller.dto.PermissaoDto;
import br.com.jupiter.crud.entity.Permissao;
import br.com.jupiter.crud.service.PermissaoService;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

  private PermissaoService permissaoService;

  @Autowired
  public PermissaoController(PermissaoService permissaoService) {
    this.permissaoService = permissaoService;
  }

  @Operation(summary = "Retorna todas as permissões.")
  @GetMapping
  public List<PermissaoDto> getAll() {
    List<Permissao> allPermissoes = permissaoService.getAll();
    return allPermissoes.stream().map(PermissaoDto::fromEntity).toList();
  }

  @Operation(summary = "Retorna uma permissão especifica com base no ID.", description = "Retorna uma permissão especifica com base no ID fornecido como parâmetro.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "ID da permissão encontrado"),
    @ApiResponse(responseCode = "404", description = "ID da permissão não encontrado")
  })
  @GetMapping("/{id}")
  public PermissaoDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.getById(id)
    );
  }

  @Operation(summary = "Retorna uma permissão especifica com base na String.", description = "Retorna uma permissão específica com base na String fornecido como parâmetro no search.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Nome da permissão encontrado"),
    @ApiResponse(responseCode = "404", description = "Nome da permissão não encontrado")
  })
  @GetMapping("search")
  public List<Permissao> getbyName(@RequestParam String permissao) throws NameNotFoundException {
    return permissaoService.getByName(permissao);
  }

  @Operation(summary = "Criar uma nova permissão.")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PermissaoDto salvar(@RequestBody PermissaoCreationDto permissaoCreationDto) {
    return PermissaoDto.fromEntity(
      permissaoService.salvar(permissaoCreationDto.toEntity())
    );
  }

  @Operation(summary = "Atualizar uma permissão.", description = "Atualizar uma permissão com base no ID fornecido como parâmetro.")
  @PutMapping("/{id}")
  public PermissaoDto editar(@PathVariable Long id, @RequestBody PermissaoCreationDto permissaoCreationDto) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.editar(id, permissaoCreationDto.toEntity())
    );
  }

  @Operation(summary = "Deleta uma permissão.", description = "Deleta uma permissão com base no ID fornecido como parâmetro.")
  @DeleteMapping("/{id}")
  public PermissaoDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.delete(id)
    );
  }
}
