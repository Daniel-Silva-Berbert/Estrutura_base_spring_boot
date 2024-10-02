package br.com.jupiter.crud.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name="usuarios")
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String userName;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Permissao permissao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true)
    private Pessoa pessoa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id", nullable = true)
    private Cargo cargo;

    public Usuario(){}

    public Usuario(Long id, String userName, String email, String password, Permissao permissao, Pessoa pessoa, Cargo cargo)
    {
        setId(id);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
        setPermissao(permissao);
        setPessoa(pessoa);
        setCargo(cargo);
    }

    public Usuario(String userName, String email  ,String password, Permissao permissao) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.permissao = permissao;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Long getId() {
        return id;
    }
    
    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.permissao == Permissao.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    
}


