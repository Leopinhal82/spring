package com.projeto.modelo.spring.controller;

import com.projeto.modelo.spring.service.ArquivoService;
import com.projeto.modelo.spring.service.ExcelService;
import com.projeto.modelo.spring.service.ProcessamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/processamento")
public class ProcessamentoController {

    @Autowired
    ProcessamentoService processamentoService;

    @Autowired
    ArquivoService arquivoService;

    @Autowired
    ExcelService excelService;

    @PostMapping()
    public void executarProcessamento(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
        processamentoService.executarProcesso(reapExcelDataFile);
    }

    @PostMapping(value = "/{nomeArquivo}")
    public void gerarArquivo(@PathVariable String nomeArquivo) throws IOException {
        var listaArquivos = arquivoService.findAll();
        excelService.escreverArquivo(nomeArquivo, listaArquivos);
    }
}
