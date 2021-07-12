package com.projeto.modelo.spring.entity;

import com.projeto.modelo.spring.dto.ArquivoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document(collection = "arquivo")
public class Arquivo {

    @Id
    private String id;
    private String data;
    private String nome;
    private String conteudo;

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
}
