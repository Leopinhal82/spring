package com.projeto.modelo.spring.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArquivoDto {

    private String id;
    private String data;
    private String nome;
    private String conteudo;
    private String detalhes;

    public ArquivoDto(String nome, String conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

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
