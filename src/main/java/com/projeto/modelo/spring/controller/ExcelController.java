package com.projeto.modelo.spring.controller;

import com.projeto.modelo.spring.entity.Arquivo;
import com.projeto.modelo.spring.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping("/import")
    public List<Arquivo> mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
        return excelService.lerExcel(reapExcelDataFile);
    }
}
