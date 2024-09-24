package br.com.jupiter.crud.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String nome;

	private String userName;

	private String email;

	private String password;

	private Permissao permissoes;

	private Cargo cargo;

	private Projeto[] projeto;

    public Usuario(){}

    public Usuario(Long id, String nome, String userName, String email, String password, Permissao permissoes, Cargo cargo, Projeto[] projetos)
    {
        setId(id);
        setNome(nome);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
        setPermissoes(permissoes);
        setCargo(cargo);
        setProjeto(projetos);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermissoes(Permissao permissoes) {
        this.permissoes = permissoes;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setProjeto(Projeto[] projeto) {
        this.projeto = projeto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Permissao getPermissoes() {
        return permissoes;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Projeto[] getProjeto() {
        return projeto;
    }
}
