package br.com.jupiter.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="projetos")
public class Projeto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

	private String nome;

	private Usuario[] usuario;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuario(Usuario[] usuario) {
        this.usuario = usuario;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public Usuario[] getUsuario() {
        return usuario;
    }
}
