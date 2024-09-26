package br.com.jupiter.crud.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table (name="usuarios")
public class Usuario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	  @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate nascimento;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "usuarios")
    private List<Permissao> permissoes = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
        name = "usuarios_projetos",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    private List<Projeto> projetos = new ArrayList<>();

    public Usuario(){}

    public Usuario(Long id, String nome, String cpf, LocalDate nascimento, String userName, String email, String password, Permissao permissoes, Cargo cargo, Projeto projeto)
    {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setNascimento(nascimento);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
        setPermissoes(permissoes);
        setCargo(cargo);
        setProjeto(projeto);
    }

    public Usuario(String nome, String userName, String email, String cpf, LocalDate nascimento  ,String password) {
        this.nome = nome;
        this.userName = userName;
        this.email = email;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
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
        this.permissoes.add(permissoes);
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return this.cpf;
    }
    
    public LocalDate getNascimento() {
        return this.nascimento;
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

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }
}


