package com.bettercode.connect.web.rest;


import com.bettercode.connect.excel.exception.NotSupportedFileException;
import com.bettercode.connect.web.Util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/excel-processor")
public class ExcelProcessorRestController {

    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @PutMapping
    public ResponseEntity<String> upload(@RequestParam("tenantCode") String tenantCode,
                                         @RequestParam("appCode") String appCode,
                                         @RequestParam("excelType") String excelType,
                                         @RequestParam("userId") String userId,
                                         @RequestParam("excelFile") MultipartFile excelFile) throws IOException {

        if(!FileUtil.isExcelFile(excelFile)) {
            throw new NotSupportedFileException(excelFile.getOriginalFilename() + " is not excel file!");
        }
        return new ResponseEntity<>("Hello", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> search(@PathVariable("id") String id) throws IOException {
        return new ResponseEntity<>("World", HttpStatus.OK);
    }

}