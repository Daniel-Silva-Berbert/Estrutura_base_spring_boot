package br.com.jupiter.crud.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="projetos")
public class Projeto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

	
    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "projetos")
    private List<Usuario> usuarios;

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

    public List<Usuario> getUsuario() {
        return usuarios;
    }
}
