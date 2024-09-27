package br.com.jupiter.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jupiter.crud.controller.dto.ProjetoCreationDto;
import br.com.jupiter.crud.controller.dto.ProjetoDto;
import br.com.jupiter.crud.entity.Projeto;
import br.com.jupiter.crud.service.ProjetoService;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

  private ProjetoService projetoService;

  @Autowired
  public ProjetoController(ProjetoService projetoService) {
    this.projetoService = projetoService;
  }

  @GetMapping
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Lista de projetos obtida com sucesso",
      content = @Content(
        mediaType = "application/json",
        array = @ArraySchema(
          schema = @Schema(implementation = ProjetoDto.class)
        )
      )
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Operation(summary = "Rota para listar todos os projetos.")
  public List<ProjetoDto> getAll() {
    List<Projeto> allProjetos = projetoService.getAll();
    return allProjetos.stream().map(ProjetoDto::fromEntity).toList();
  }

  @GetMapping("/{id}")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Projeto obtido com sucesso",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ProjetoDto.class)
      )
    ),
    @ApiResponse(
      responseCode = "404", description = "Projeto não encontrado",
      content = @Content(mediaType = "application/json")
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Parameter(
    name = "id",
    description = "ID do projeto para buscar.",
    required = true,
    example = "123"
    )
  @Operation(summary = "Rota para buscar um projeto pelo ID.")
  public ProjetoDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return ProjetoDto.fromEntity(
      projetoService.getById(id)
    );
  }

  @GetMapping("search")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Projeto obtido com sucesso",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ProjetoDto.class)
      )
    ),
    @ApiResponse(
      responseCode = "404", description = "Projeto não encontrado",
      content = @Content(mediaType = "application/json")
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Parameter(
    name = "Nome",
    description = "Nome do projeto para buscar.",
    required = true,
    example = "123"
    )
  @Operation(summary = "Rota para buscar um projeto pelo Nome.")
  public List<Projeto> getByName(@RequestParam String projeto) throws NameNotFoundException {
    return projetoService.getByName(projeto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "201", description = "Projeto criado com sucesso",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = ProjetoDto.class))
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Operation(summary = "Rota para criar um Projeto.")
  public ProjetoDto salvar(@RequestBody @Valid ProjetoCreationDto projetoCreationDto) {
    return ProjetoDto.fromEntity(
      projetoService.salvar(projetoCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Projeto atualizado com sucesso",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = ProjetoDto.class))
    ),
    @ApiResponse(
      responseCode = "404", description = "Projeto não encontrado",
      content = @Content(mediaType = "application/json")
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Parameter(
    name = "id",
    description = "ID do projeto para editar.",
    required = true,
    example = "123"
    )
  @Operation(summary = "Rota para editar um Projeto.")
  public ProjetoDto editar(@PathVariable Long id, @RequestBody @Valid ProjetoCreationDto projetoCreationDto) throws EntityNotFoundException {
    return ProjetoDto.fromEntity(
      projetoService.editar(id, projetoCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Projeto excluído com sucesso",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = ProjetoDto.class))
    ),
    @ApiResponse(
      responseCode = "404", description = "Projeto não encontrado",
      content = @Content(mediaType = "application/json")
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Parameter(
    name = "id",
    description = "ID do projeto para excluir.",
    required = true,
    example = "123"
    )
  @Operation(summary = "Rota para editar um Projeto.")
  public ProjetoDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return ProjetoDto.fromEntity(
      projetoService.delete(id)
    );
  }
}
