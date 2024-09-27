package br.com.jupiter.crud.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table (name="permissoes")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome deve conter apenas letras e espaços.")
    @NotBlank
	  private String nome;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
        name = "permissoes_usuarios",
        joinColumns = @JoinColumn(name = "permissoes_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios = new ArrayList<>();

    public Permissao() {}

    public Permissao(String nome) {
        this.nome = nome;
    }

    public Permissao(Long id, List<Usuario> usuarios, String nome) {
        this.id = id;
        this.usuarios = usuarios;
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
