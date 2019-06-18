package com.bettercode.connect.repository;

import com.bettercode.connect.entity.WorkExcelFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExcelFileRepository extends JpaRepository<WorkExcelFile, Long> {
}
