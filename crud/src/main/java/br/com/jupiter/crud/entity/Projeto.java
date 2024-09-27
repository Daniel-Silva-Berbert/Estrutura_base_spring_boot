package br.com.jupiter.crud.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table (name="projetos")
public class Projeto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome deve conter apenas letras e espaços.")
    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "projetos")
    private List<Usuario> usuarios = new ArrayList<>();

    public Projeto() {}

    public Projeto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Projeto(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
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
}
