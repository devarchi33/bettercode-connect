package com.bettercode.connect.repository;

import com.bettercode.connect.entity.ExcelFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExcelFileRepository extends JpaRepository<ExcelFile, Long> {
}
