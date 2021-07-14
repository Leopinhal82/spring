package com.projeto.modelo.spring.service;

import com.projeto.modelo.spring.entity.Arquivo;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    private static final String NOME_SHEET = "Arquivos";

    public List<Arquivo> lerExcel(MultipartFile reapExcelDataFile) throws IOException {

        List<Arquivo> arquivoList = new ArrayList<>();
        var workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        var worksheet = workbook.getSheetAt(0);

        //Loop em todas as linhas do arquivo.
        for (var i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
            var arquivo = new Arquivo();

            XSSFRow row = worksheet.getRow(i);
            //Lê todas as células da Linha
            arquivo.setId(row.getCell(0) == null ? "" : row.getCell(0).getStringCellValue());
            arquivo.setNome(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());
            arquivo.setConteudo(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());
            arquivo.setData(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());
            arquivo.setCpf(row.getCell(4) == null ? "" : row.getCell(4).getStringCellValue());
            arquivoList.add(arquivo);
        }

        return arquivoList;
    }

    public void escreverArquivo(String nomeArquivo, List<Arquivo> listArquivo) throws IOException {

        try(var workbook = new XSSFWorkbook();) {
            var worksheet = workbook.createSheet(NOME_SHEET);

            Row row;

            row = worksheet.createRow(0);

            row.createCell(0).setCellValue("Código");
            row.createCell(1).setCellValue("Nome");
            row.createCell(2).setCellValue("Conteúdo");
            row.createCell(3).setCellValue("Data");

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            row.getCell(0).setCellStyle(headerStyle);
            row.getCell(1).setCellStyle(headerStyle);
            row.getCell(2).setCellStyle(headerStyle);
            row.getCell(3).setCellStyle(headerStyle);


            for (var x = 0; x < listArquivo.size(); x++) {
                row = worksheet.createRow(x+1);
                row.createCell(0).setCellValue(listArquivo.get(x).getId());
                row.createCell(1).setCellValue(listArquivo.get(x).getNome());
                row.createCell(2).setCellValue(listArquivo.get(x).getConteudo());
                row.createCell(3).setCellValue(listArquivo.get(x).getData());

            }

            //Criando arquivo novo nomeArquivo
            var ops = new FileOutputStream(nomeArquivo.concat(".xlsx"));
            workbook.write(ops);
            ops.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


}
