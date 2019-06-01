package com.bettercode.connect.service;

import com.bettercode.connect.entity.ExcelFile;

public interface IUploadService {
    boolean isRegisteredExcelType(String tenantCode, String appCode, String excelType);
    ExcelFile saveUploadExcelFile(ExcelFile excelFile);
    String findJsonFormatExcelFile(Long id);
}
