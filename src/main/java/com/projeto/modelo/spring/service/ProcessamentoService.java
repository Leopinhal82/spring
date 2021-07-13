package com.projeto.modelo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProcessamentoService {

    @Autowired
    ExcelService excelService;

    @Autowired
    ProducerService producerService;

    public void executarProcesso(MultipartFile reapExcelDataFile) throws IOException {
        // Ler Arquivo
        var listArquivo = excelService.lerExcel(reapExcelDataFile);
        // Inserir dados do Arquivo Lido na Fila
        producerService.enviarMensagem(listArquivo);
    }
}
