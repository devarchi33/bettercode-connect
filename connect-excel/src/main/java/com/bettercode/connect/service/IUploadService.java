package com.bettercode.connect.service;

import com.bettercode.connect.entity.ExcelFile;

import java.io.IOException;

public interface IUploadService {
    boolean isRegisteredExcelType(String tenantCode, String appCode, String excelType);
    String parsingExcelFileToJson(ExcelFile file) throws IOException;
}
