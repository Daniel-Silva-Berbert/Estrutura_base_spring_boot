package br.com.jupiter.crud.entity;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table (name="cargos")
public class Cargo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O cargo deve conter apenas letras e espaços.")
    @NotBlank(message = "O campo nome é obrigatorio")
    private String nome;

    @NotNull(message = "O campo remuneracao é obrigatorio")
	  private Float remuneracao;

    @OneToMany(mappedBy = "cargo")
    private List<Usuario> usuarios;

    public Cargo(){}
    public Cargo(String nome, Float remuneracao)
    {
        setNome(nome);
        setRemuneracao(remuneracao);
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setRemuneracao(Float remuneracao) {
        this.remuneracao = remuneracao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Float getRemuneracao() {
        return remuneracao;
    }
}
