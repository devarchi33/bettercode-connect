package com.bettercode.connect.excel.service;

import com.bettercode.connect.excel.entity.ExcelFile;

import java.io.IOException;

public interface UploadService {
    boolean isRegisteredExcelType(String tenantCode, String appCode, String excelType);
    String parsingExcelFileToJson(ExcelFile file) throws IOException;
}
