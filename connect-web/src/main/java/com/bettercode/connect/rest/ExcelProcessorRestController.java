package com.bettercode.connect.rest;


import com.bettercode.connect.utils.FileUtil;
import com.bettercode.connect.entity.WorkExcelFile;
import com.bettercode.connect.exception.NotRegisteredException;
import com.bettercode.connect.exception.NotSupportedFileException;
import com.bettercode.connect.service.IUploadService;
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

  private IUploadService uploadService;

  @Autowired
  public void setUploadService(IUploadService uploadService) {
    this.uploadService = uploadService;
  }

  @PostMapping
  public ResponseEntity<String> upload(@RequestParam("tenantCode") String tenantCode,
                                       @RequestParam("appCode") String appCode,
                                       @RequestParam("excelType") String excelType,
                                       @RequestParam("userId") String userId,
                                       @RequestParam("excelFile") MultipartFile excelFile) throws IOException {

    if(!FileUtil.isExcelFile(excelFile)) {
      throw new NotSupportedFileException(excelFile.getOriginalFilename() + " is not excel file!");
    }

    if(!uploadService.isRegisteredExcelType(tenantCode, appCode, excelType)) {
      throw new NotRegisteredException(tenantCode + ", " + appCode + ", " + excelType + " is not registered!");
    }

    WorkExcelFile createdExcelFile = uploadService.saveUploadExcelFile(new WorkExcelFile(tenantCode, appCode, excelType, excelFile, userId));
    return new ResponseEntity<>(createdExcelFile.getLinkUrl(env), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<String> search(@PathVariable("id") Long id) {
    return new ResponseEntity<>(uploadService.findJsonFormatExcelFile(id), HttpStatus.OK);
  }

}