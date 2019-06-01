package com.bettercode.connect.excel.service.impl;

import com.bettercode.connect.excel.engine.ExcelRowMapper;
import com.bettercode.connect.excel.engine.ExcelTemplate;
import com.bettercode.connect.excel.engine.mapper.BarrelOmniStock;
import com.bettercode.connect.excel.engine.mapper.Product;
import com.bettercode.connect.excel.entity.ExcelFile;
import com.bettercode.connect.excel.entity.ExcelTypeId;
import com.bettercode.connect.excel.exception.NotRegisteredException;
import com.bettercode.connect.excel.repository.IExcelTypeRepository;
import com.bettercode.connect.excel.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {
    private final static Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    private IExcelTypeRepository excelTypeRepository;

    @Autowired
    public void setExcelTypeRepository(IExcelTypeRepository excelTypeRepository) {
        this.excelTypeRepository = excelTypeRepository;
    }

    private ExcelRowMapper<Product> productExcelRowMapper;

    @Autowired
    public void setProductExcelRowMapper(ExcelRowMapper<Product> productExcelRowMapper) {
        this.productExcelRowMapper = productExcelRowMapper;
    }

    private ExcelRowMapper<BarrelOmniStock> barrelOmniStockExcelRowMapper;

    @Autowired
    public void setBarrelOmniStockExcelRowMapper(ExcelRowMapper<BarrelOmniStock> barrelOmniStockExcelRowMapper) {
        this.barrelOmniStockExcelRowMapper = barrelOmniStockExcelRowMapper;
    }

    @Override
    public boolean isRegisteredExcelType(String tenantCode, String appCode, String excelType) {
        return excelTypeRepository.existsById(new ExcelTypeId(tenantCode, appCode, excelType));
    }

    @Override
    public String parsingExcelFileToJson(ExcelFile uploadExcelFile) {
        ExcelTemplate excelTemplate = new ExcelTemplate();
        return excelTemplate.getRows(uploadExcelFile.getExcelFile(), getExcelItemProcessor(uploadExcelFile)).toString();
    }

    private ExcelRowMapper getExcelItemProcessor(ExcelFile uploadExcelFile) {
        if(uploadExcelFile.getExcelType().equalsIgnoreCase("product")) {
            return productExcelRowMapper;
        } else if(uploadExcelFile.getTenantCode().equalsIgnoreCase("bettercode")) {
            if(uploadExcelFile.getAppCode().equalsIgnoreCase("connect")) {
                if(uploadExcelFile.getExcelType().equalsIgnoreCase("stock")) {
                    return barrelOmniStockExcelRowMapper;
                } else {
                    throw new NotRegisteredException(uploadExcelFile.getTenantCode() + "," + uploadExcelFile.getAppCode() + "," + uploadExcelFile.getExcelType() + " is not registered type!");
                }
            } else {
                throw new NotRegisteredException(uploadExcelFile.getTenantCode() + "," + uploadExcelFile.getAppCode() + " is not registered type!");
            }
        } else {
            throw new NotRegisteredException(uploadExcelFile.getTenantCode() + " is not registered type!");
        }
    }
}
