package br.com.jupiter.crud.controller;

import br.com.jupiter.crud.controller.dto.PermissaoCreationDto;
import br.com.jupiter.crud.controller.dto.PermissaoDto;
import br.com.jupiter.crud.entity.Permissao;
import br.com.jupiter.crud.service.PermissaoService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController
@RequestMapping("/permissoes")*/
public class PermissaoController {

  /*private PermissaoService permissaoService;

  @Autowired
  public PermissaoController(PermissaoService permissaoService) {
    this.permissaoService = permissaoService;
  }

  @Operation(summary = "Retorna todas as permissões.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "ID do permissão encontrado",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PermissaoDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @GetMapping
  public List<PermissaoDto> getAll() {
    List<Permissao> allPermissoes = permissaoService.getAll();
    return allPermissoes.stream().map(PermissaoDto::fromEntity).toList();
  }

  @Operation(summary = "Retorna uma permissão especifica com base no ID.", description = "Retorna uma permissão especifica com base no ID fornecido como parâmetro.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "ID do permissão encontrado",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PermissaoDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "404", description = "ID do permissão não encontrado", content = @Content(mediaType = "")),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "")
    )
  })
  @Parameter(name = "id",description = "ID do permissão.",required = true,example = "321")
  @GetMapping("/{id}")
  public PermissaoDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.getById(id)
    );
  }

  @Operation(summary = "Retorna uma permissão especifica com base na String.", description = "Retorna uma permissão específica com base na String fornecido como parâmetro no search.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Nome da permissão encontrado",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PermissaoDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "404", description = "Nome do permissão não encontrado", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @Parameter(name = "permissao",description = "Nome do permissão para buscar.",required = true,example = "create")
  @GetMapping("search")
  public List<Permissao> getbyName(@RequestParam String permissao) throws NameNotFoundException {
    return permissaoService.getByName(permissao);
  }

  @Operation(summary = "Criar uma nova permissão.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Permissão criada com sucesso",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PermissaoCreationDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PermissaoDto salvar(@RequestBody @Valid PermissaoCreationDto permissaoCreationDto) {
    return PermissaoDto.fromEntity(
      permissaoService.salvar(permissaoCreationDto.toEntity())
    );
  }

  @Operation(summary = "Atualizar uma permissão.", description = "Atualizar uma permissão com base no ID fornecido como parâmetro.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Permissão atualizada com sucesso",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PermissaoCreationDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @Parameter(name = "id",description = "ID do cargo.",required = true,example = "123")
  @PutMapping("/{id}")
  public PermissaoDto editar(@PathVariable Long id, @RequestBody @Valid PermissaoCreationDto permissaoCreationDto) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.editar(id, permissaoCreationDto.toEntity())
    );
  }

  @Operation(summary = "Deleta uma permissão.", description = "Deleta uma permissão com base no ID fornecido como parâmetro.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Permissão deletado com sucesso",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PermissaoDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "404", description = "ID da permissão não encontrado", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @Parameter(name = "id",description = "ID da permissão.",required = true,example = "123")
  @DeleteMapping("/{id}")
  public PermissaoDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.delete(id)
    );
  }*/
}
