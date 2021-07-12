package com.projeto.modelo.spring.controller;

import com.projeto.modelo.spring.dto.DadosDto;
import com.projeto.modelo.spring.entity.Dados;
import com.projeto.modelo.spring.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "dados")
public class DadosController {

    @Autowired
    private ProducerService producerService;

    @PutMapping
    public ResponseEntity<Void> alterarDados(@RequestBody DadosDto dados) {

        var persistentDados =  new Dados(dados);
        System.out.println("Enviando para Fila Código: " + persistentDados.getCodigo() + " Nome: " + persistentDados.getNome());
        producerService.enviaMensagem(persistentDados);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
