package com.projeto.modelo.spring.service;

import com.projeto.modelo.spring.entity.Arquivo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    public List<Arquivo> lerExcel(MultipartFile reapExcelDataFile) throws IOException {

        List<Arquivo> arquivoList = new ArrayList<>();
        var workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        var worksheet = workbook.getSheetAt(0);

        worksheet.createRow(4).createCell(1).setCellValue("Teste do Humberto");

        //Loop em todas as linhas do arquivo.
        for (var i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
            var arquivo = new Arquivo();

            XSSFRow row = worksheet.getRow(i);
            //Lê todas as células da Linha
            arquivo.setId(row.getCell(0) == null? "" : row.getCell(0).getStringCellValue());
            arquivo.setNome(row.getCell(1) == null? "" : row.getCell(1).getStringCellValue());
            arquivo.setConteudo(row.getCell(2) == null? "" : row.getCell(2).getStringCellValue());
            arquivo.setData(row.getCell(3) == null? "" : row.getCell(3).getDateCellValue().toString());
            arquivoList.add(arquivo);
        }

        //Criando arquivo novo MeuArquivo.xlsx
        var ops = new FileOutputStream("MeuArquivo.xlsx");
        workbook.write(ops);

        return arquivoList;
    }

}
