package com.bettercode.connect.service.impl;

import com.bettercode.connect.engine.ExcelRowMapper;
import com.bettercode.connect.engine.ExcelTemplate;
import com.bettercode.connect.entity.ExcelFile;
import com.bettercode.connect.entity.ExcelTypeId;
import com.bettercode.connect.exception.NotRegisteredException;
import com.bettercode.connect.exception.NotSupportedFileException;
import com.bettercode.connect.repository.IExcelFileRepository;
import com.bettercode.connect.repository.IExcelTypeRepository;
import com.bettercode.connect.service.IUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Optional;

@Service
public class UploadServiceImpl implements IUploadService {
  private final static Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

  private ApplicationContext applicationContext;

  @Autowired
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  private IExcelTypeRepository excelTypeRepository;

  @Autowired
  public void setExcelTypeRepository(IExcelTypeRepository excelTypeRepository) {
    this.excelTypeRepository = excelTypeRepository;
  }

  private IExcelFileRepository excelFileRepository;

  @Autowired
  public void setExcelFileRepository(IExcelFileRepository excelFileRepository) {
    this.excelFileRepository = excelFileRepository;
  }

  @Override
  public boolean isRegisteredExcelType(String tenantCode, String appCode, String excelType) {
    return excelTypeRepository.existsById(new ExcelTypeId(tenantCode, appCode, excelType));
  }

  @Override
  public ExcelFile saveUploadExcelFile(ExcelFile excelFile) {
    return excelFileRepository.save(excelFile);
  }

  @Override
  public String findJsonFormatExcelFile(Long id) {
    Optional<ExcelFile> optional = excelFileRepository.findById(id);
    if(optional.isPresent()) {
      return parsingExcelFileToJson(optional.get());
    }
    throw new NoResultException("ID: " + id + ", ExcelFile is not exists.");
  }

  private String parsingExcelFileToJson(ExcelFile uploadExcelFile) {
    ExcelTemplate excelTemplate = new ExcelTemplate();
    try {
      return excelTemplate.getRows(uploadExcelFile, getExcelItemProcessor(uploadExcelFile)).toString();
    } catch (IOException e) {
      throw new NotSupportedFileException("This Excel file format can't parsing..");
    }
  }

  private ExcelRowMapper getExcelItemProcessor(ExcelFile uploadExcelFile) {
    try {
      return (ExcelRowMapper) applicationContext.getBean(uploadExcelFile.getRowMapperName());
    } catch (BeansException e) {
      throw new NotRegisteredException(uploadExcelFile.getTenantCode() + "," + uploadExcelFile.getAppCode() + "," + uploadExcelFile.getExcelType() + " is not registered type!");
    }
  }
}
