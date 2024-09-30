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

import br.com.jupiter.crud.controller.dto.UsuarioCreationDto;
import br.com.jupiter.crud.controller.dto.UsuarioDto;
import br.com.jupiter.crud.entity.Usuario;
import br.com.jupiter.crud.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

  private UsuarioService usuarioService;

  @Autowired
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Lista de usuários obtida com sucesso",
      content = @Content(
        mediaType = "application/json",
        array = @ArraySchema(
          schema = @Schema(implementation = UsuarioDto.class)
        )
      )
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
})
  @Operation(summary = "Rota para listar todos os Usuários.")
  public List<UsuarioDto> getAll() {
    List<Usuario> allUsuarios = usuarioService.getAll();
    return allUsuarios.stream().map(UsuarioDto::fromEntity).toList();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Rota para buscar um Usuário por ID.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Usuário encontrado com sucesso",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = UsuarioDto.class))
    ),
    @ApiResponse(
      responseCode = "404", description = "Usuário não encontrado",
      content = @Content(mediaType = "application/json")
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Parameter(
    name = "id",
    description = "ID do usuário para buscar.",
    required = true,
    example = "123"
    )
  public UsuarioDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.getById(id)
    );
  }

  @GetMapping("search")
  @Operation(summary = "Rota para buscar um Usuário pelo nome.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Usuário encontrado com sucesso",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = UsuarioDto.class))
    ),
    @ApiResponse(
      responseCode = "404", description = "Usuário não encontrado",
      content = @Content(mediaType = "application/json")
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Parameter(
        name = "Nome",
        description = "Nome do usuário para buscar.",
        required = true,
        example = "Júlio"
    )
  public List<Usuario> getByName(@RequestParam String usuario) throws NameNotFoundException {
    return usuarioService.getByName(usuario);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "201", description = "Usuário criado com sucesso",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = UsuarioDto.class))
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Operation(summary = "Rota para criar um Usuário.")
public UsuarioDto salvar(@RequestBody @Valid  UsuarioCreationDto usuarioCreationDto) {
    return UsuarioDto.fromEntity(
      usuarioService.salvar(usuarioCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Usuário atualizado com sucesso",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = UsuarioDto.class))
    ),
    @ApiResponse(
      responseCode = "404", description = "Usuário não encontrado",
      content = @Content(mediaType = "application/json")
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Operation(summary = "Rota para editar um Usuário.")
  @Parameter(
        name = "id",
        description = "ID do usuário para editar.",
        required = true,
        example = "123"
    )
  public UsuarioDto editar(@PathVariable Long id, @RequestBody @Valid UsuarioCreationDto usuarioCreationDto)
    throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.editar(id, usuarioCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200", description = "Usuário deletado com sucesso",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UsuarioDto.class)
        )
    ),
    @ApiResponse(
      responseCode = "404", description = "Usuário não encontrado",
      content = @Content(mediaType = "application/json")
    ),
    @ApiResponse(
      responseCode = "500", description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
    )
  })
  @Operation(summary = "Rota para deletar um usuário por ID.")
  @Parameter(
        name = "id",
        description = "ID do usuário a ser deletado.",
        required = true,
        example = "123"
    )
  public UsuarioDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.delete(id)
    );
  }

  @PutMapping("/{usuarioId}/cargo/{cargoId}")
  @ApiResponses(value = {
        @ApiResponse(
          responseCode = "200",
          description = "Cargo do usuário definido com sucesso",
          content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UsuarioDto.class)
            )
          ),
        @ApiResponse(
          responseCode = "400",
          description = "ID inválido ou não encontrado",
          content = @Content(mediaType = "application/json")
          ),
        @ApiResponse(
          responseCode = "404",
          description = "Usuário ou cargo não encontrado",
          content = @Content(mediaType = "application/json")
          ),
        @ApiResponse(
          responseCode = "500",
          description = "Erro interno do servidor",
          content = @Content(mediaType = "application/json")
          )
    })
  @Operation(summary = "Rota para definir o cargo de um Usuário.")
  @Parameter(
        name = "usuarioId",
        description = "ID do usuário.",
        required = true,
        example = "123"
    )
    @Parameter(
        name = "cargoId",
        description = "ID do cargo.",
        required = true,
        example = "123"
    )
  public UsuarioDto setUsuarioCargo(@PathVariable Long usuarioId, @PathVariable Long cargoId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.setUsuarioCargo(usuarioId, cargoId)
    );
  }

  @DeleteMapping("/{usuarioId}/cargo")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Cargo do usuário removido com sucesso",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UsuarioDto.class)
        )
      ),
    @ApiResponse(
      responseCode = "400",
      description = "ID inválido ou não encontrado",
      content = @Content(mediaType = "application/json")
      ),
    @ApiResponse(
      responseCode = "404",
      description = "Usuário ou cargo não encontrado",
      content = @Content(mediaType = "application/json")
      ),
    @ApiResponse(
      responseCode = "500",
      description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
      )
})
  @Operation(summary = "Rota para remover o usuário de um cargo.")
  @Parameter(
        name = "usuarioId",
        description = "ID do usuário.",
        required = true,
        example = "123"
    )
  public UsuarioDto removeUsuarioCargo(@PathVariable Long usuarioId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.removeUsuarioCargo(usuarioId)
    );
  }

  
  /*@PutMapping("/{usuarioId}/permissao/{permissaoId}")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Permissão do usuário definida com sucesso",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UsuarioDto.class)
        )
      ),
    @ApiResponse(
      responseCode = "400",
      description = "ID inválido ou não encontrado",
      content = @Content(mediaType = "application/json")
      ),
    @ApiResponse(
      responseCode = "404",
      description = "Usuário ou permissão não encontrada",
      content = @Content(mediaType = "application/json")
      ),
    @ApiResponse(
      responseCode = "500",
      description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
      )
  })
  @Operation(summary = "Rota para definir uma permissão a um Usuário.")
  @Parameter(
        name = "usuarioId",
        description = "ID do usuário.",
        required = true,
        example = "123"
    )
    @Parameter(
        name = "permissaoId",
        description = "ID da permissão.",
        required = true,
        example = "123"
    )
  public UsuarioDto addUsuarioPermissao(@PathVariable Long usuarioId, @PathVariable Long permissaoId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.addUsuarioPermissao(usuarioId, permissaoId)
    );
  }

  @DeleteMapping("/{usuarioId}/permissao/{permissaoId}")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Permissão do usuário removida com sucesso",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UsuarioDto.class)
        )
      ),
    @ApiResponse(
      responseCode = "400",
      description = "ID inválido ou não encontrado",
      content = @Content(mediaType = "application/json")
      ),
    @ApiResponse(
      responseCode = "404",
      description = "Usuário ou permissão não encontrada",
      content = @Content(mediaType = "application/json")
      ),
    @ApiResponse(
      responseCode = "500",
      description = "Erro interno do servidor",
      content = @Content(mediaType = "application/json")
      )
})
  @Operation(summary = "Rota para retirar a permissão a um Usuário.")
  @Parameter(
        name = "usuarioId",
        description = "ID do usuário.",
        required = true,
        example = "123"
    )
    @Parameter(
        name = "permissaoId",
        description = "ID da permissão.",
        required = true,
        example = "123"
    )
  public UsuarioDto removeUsuarioPermissao(@PathVariable Long usuarioId, @PathVariable Long permissaoId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.removeUsuarioPermissao(usuarioId, permissaoId)
    );
  }*/
}
