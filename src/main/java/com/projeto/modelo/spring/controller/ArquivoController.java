package com.projeto.modelo.spring.controller;

import com.projeto.modelo.spring.dto.ArquivoDto;
import com.projeto.modelo.spring.entity.Arquivo;
import com.projeto.modelo.spring.service.ArquivoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "arquivo")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @ApiOperation(value = "Retorna uma lista de Arquivos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de arquivo"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(produces="application/json")
    public List<Arquivo> listarArquivos() {
        return arquivoService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> inserirArquivo(@RequestBody ArquivoDto arquivoDto) {
        var persistentArquivo = new Arquivo(arquivoDto);

        arquivoService.inserir(persistentArquivo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarArquivo(@PathVariable String id) {
        arquivoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<Optional<Arquivo>> buscaArquivo(@PathVariable String id){
        Optional<Arquivo> arquivo = arquivoService.findById(id);

        if(!arquivo.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(arquivo);
    }

    @GetMapping("/contar")
    public long contarArquivos(){
        return arquivoService.count();
    }

    @PutMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<Arquivo> atualizaProduto(@PathVariable String id, @Valid @RequestBody ArquivoDto arquivoDto) {

        var persistentArquivo = new Arquivo(arquivoDto);
        return arquivoService.atualizar(id, persistentArquivo);
    }
}
