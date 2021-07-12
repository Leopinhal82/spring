package com.projeto.modelo.spring.service;

import com.projeto.modelo.spring.entity.Arquivo;
import com.projeto.modelo.spring.repository.ArquivoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    public void inserir(Arquivo arquivo) {
        arquivoRepository.save(arquivo);
    }

    public ResponseEntity<Arquivo> atualizar(String id, Arquivo arquivo) {

        Optional<Arquivo> existente = findById(id);
        if(!existente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(arquivo, existente.get(), "id");

        arquivoRepository.save(existente.get());

        return ResponseEntity.ok(existente.get());
    }

    public List<Arquivo> findAll() {
        return arquivoRepository.findAll();
    }

    public long count() {
        return arquivoRepository.count();
    }

    public Optional<Arquivo> findById(String id) {
        return arquivoRepository.findById(id);
    }

    public void delete(String id) {
        arquivoRepository.deleteById (id);
    }

}
