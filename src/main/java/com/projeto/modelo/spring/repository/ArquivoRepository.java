package com.projeto.modelo.spring.repository;

import com.projeto.modelo.spring.entity.Arquivo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArquivoRepository extends MongoRepository<Arquivo, String> {
}