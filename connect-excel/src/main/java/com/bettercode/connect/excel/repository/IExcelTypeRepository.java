package com.bettercode.connect.excel.repository;

import com.bettercode.connect.excel.entity.ExcelType;
import com.bettercode.connect.excel.entity.ExcelTypeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExcelTypeRepository extends JpaRepository<ExcelType, ExcelTypeId> {
}
