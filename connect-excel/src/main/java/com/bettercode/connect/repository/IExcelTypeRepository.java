package com.bettercode.connect.repository;

import com.bettercode.connect.entity.ExcelTypeId;
import com.bettercode.connect.entity.ExcelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExcelTypeRepository extends JpaRepository<ExcelType, ExcelTypeId> {
}
