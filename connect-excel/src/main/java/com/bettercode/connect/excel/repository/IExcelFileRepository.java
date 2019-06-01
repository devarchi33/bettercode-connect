package com.bettercode.connect.excel.repository;

import com.bettercode.connect.excel.entity.ExcelFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExcelFileRepository extends JpaRepository<ExcelFile, Long> {
}
