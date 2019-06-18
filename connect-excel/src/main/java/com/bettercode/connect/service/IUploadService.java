package com.bettercode.connect.service;

import com.bettercode.connect.entity.WorkExcelFile;

public interface IUploadService {
    boolean isRegisteredExcelType(String tenantCode, String appCode, String excelType);
    WorkExcelFile saveUploadExcelFile(WorkExcelFile excelFile);
    String findJsonFormatExcelFile(Long id);
}
