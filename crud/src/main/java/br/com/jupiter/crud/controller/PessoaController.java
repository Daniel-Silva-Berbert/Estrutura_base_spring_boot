package br.com.jupiter.crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jupiter.crud.controller.dto.PermissaoCreationDto;
import br.com.jupiter.crud.controller.dto.PessoaCreationDto;
import br.com.jupiter.crud.controller.dto.PessoaDto;
import br.com.jupiter.crud.entity.Pessoa;
import br.com.jupiter.crud.service.PessoaService;
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
@RequestMapping("/pessoas")
public class PessoaController {
 private PessoaService pessoaService;

  @Autowired
  public PessoaController(PessoaService pessoaService) {
    this.pessoaService = pessoaService;
  }

  @Operation(summary = "Retorna todas as pessoas.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "ID do pessoas encontrado",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PessoaDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @GetMapping
  public List<PessoaDto> getAll() {
    List<Pessoa> allPessoas = pessoaService.getAll();
    return allPessoas.stream().map(PessoaDto::fromEntity).toList();
  }

  @Operation(summary = "Retorna uma pessoa especifica com base no ID.", description = "Retorna uma pessoa especifica com base no ID fornecido como parâmetro.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "ID do pessoa encontrado",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PessoaDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "404", description = "ID do pessoa não encontrado", content = @Content(mediaType = "")),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "")
    )
  })
  @Parameter(name = "id",description = "ID do pessoa.",required = true,example = "321")
  @GetMapping("/{id}")
  public PessoaDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return PessoaDto.fromEntity(
      pessoaService.getById(id)
    );
  }

  @Operation(summary = "Retorna uma pessoa especifica com base na String.", description = "Retorna uma pessoa específica com base na String fornecido como parâmetro no search.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Nome da pessoa encontrado",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PessoaDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "404", description = "Nome do pessoa não encontrado", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @Parameter(name = "pessoa",description = "Nome do pessoa para buscar.",required = true,example = "Daniel")
  @GetMapping("search")
  public List<Pessoa> getbyName(@RequestParam String pessoa) throws NameNotFoundException {
    return pessoaService.getByName(pessoa);
  }

  @Operation(summary = "Criar uma nova pessoa.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PessoaCreationDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PessoaDto salvar(@RequestBody @Valid PessoaCreationDto pessoaCreationDto) {
    return PessoaDto.fromEntity(
      pessoaService.salvar(pessoaCreationDto.toEntity())
    );
  }

  @Operation(summary = "Atualizar uma pessoa.", description = "Atualizar uma pessoa com base no ID fornecido como parâmetro.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PermissaoCreationDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @Parameter(name = "id",description = "ID do Pessoa.",required = true,example = "123")
  @PutMapping("/{id}")
  public PessoaDto editar(@PathVariable Long id, @RequestBody @Valid PessoaCreationDto pessoaCreationDto) throws EntityNotFoundException {
    return PessoaDto.fromEntity(
      pessoaService.editar(id, pessoaCreationDto.toEntity())
    );
  }

  @Operation(summary = "Deleta uma pessoa.", description = "Deleta uma pessoa com base no ID fornecido como parâmetro.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pessoa deletado com sucesso",content = @Content(
      mediaType = "application/json",
      array = @ArraySchema(
        schema = @Schema(implementation = PessoaDto.class)
      )
    )),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "404", description = "ID da pessoa não encontrado", content = @Content(mediaType = "")),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",content = @Content(mediaType = "")
    )
  })
  @Parameter(name = "id",description = "ID da pessoa.",required = true,example = "123")
  @DeleteMapping("/{id}")
  public PessoaDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return PessoaDto.fromEntity(
      pessoaService.delete(id)
    );
  }
}
