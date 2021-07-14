package com.projeto.modelo.spring.entity;

import com.projeto.modelo.spring.dto.ArquivoDto;
import com.projeto.modelo.spring.util.AnnotationCPFValidator;
import com.projeto.modelo.spring.util.AnnotationDateValidator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Document(collection = "arquivo")
public class Arquivo {

    @Id
    private String id;

    @AnnotationDateValidator
    public String data;
    @NotEmpty(message = "O Nome não pode ser vazio.")
    @Size(max = 6, message = "O Nome deve ter no máximo 6 posições")
    public String nome;
    private String conteudo;
    @AnnotationCPFValidator
    private String Cpf;

    public Arquivo(ArquivoDto arquivoDto) {
        this.id = arquivoDto.getId();
        this.data = arquivoDto.getData();
        this.nome = arquivoDto.getNome();
        this.conteudo = arquivoDto.getConteudo();
    }

    public Arquivo() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getCpf() { return Cpf;}

    public void setCpf(String cpf) { Cpf = cpf; }
}
