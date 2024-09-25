package br.com.jupiter.crud.entity;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="cargos")
public class Cargo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

	private float remuneracao;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public Cargo(){}
    public Cargo(Long id, String nome, float remuneracao)
    {
        setId(id);
        setNome(nome);
        setRemuneracao(remuneracao);
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setRemuneracao(float remuneracao) {
        this.remuneracao = remuneracao;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getRemuneracao() {
        return remuneracao;
    }
}
