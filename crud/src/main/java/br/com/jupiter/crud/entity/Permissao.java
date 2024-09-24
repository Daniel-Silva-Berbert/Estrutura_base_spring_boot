package br.com.jupiter.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="permissoes")
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private int nome;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public int getNome() {
        return nome;
    }
}
