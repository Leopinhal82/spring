package com.projeto.modelo.spring.entity;
import com.projeto.modelo.spring.dto.DadosDto;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dados {

    @Id
    private int codigo;
    private String nome;

    public Dados(DadosDto dadosDto) {
        this.codigo = dadosDto.getCodigo();
        this.nome = dadosDto.getNome();
    }

    public Dados(String nome) {
        this.nome = nome;
    }

    public Dados() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
