package com.projeto.modelo.spring.service;

import com.projeto.modelo.spring.entity.Arquivo;
import com.projeto.modelo.spring.util.factory.Validador;
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

    @Autowired
    Validador validador;

    public void executarProcesso(MultipartFile reapExcelDataFile) throws IOException {
        // Ler Arquivo
        var listArquivo = excelService.lerExcel(reapExcelDataFile);

        for (Arquivo item: listArquivo) {
            if(validador.validar(item)){
                // Inserir dados do Arquivo Lido na Fila
                producerService.enviarMensagem(item);
            }
        }
    }
}
