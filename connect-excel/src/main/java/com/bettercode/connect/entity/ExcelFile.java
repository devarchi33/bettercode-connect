package com.bettercode.connect.entity;

import org.springframework.core.env.Environment;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Table
@Entity(name = "upload_excel_file")
public class ExcelFile {

  // GeneratedValue - https://www.popit.kr/%ED%95%98%EC%9D%B4%EB%B2%84%EB%84%A4%EC%9D%B4%ED%8A%B8%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%9E%90%EB%8F%99-%ED%82%A4-%EC%83%9D%EC%84%B1-%EC%A0%84%EB%9E%B5%EC%9D%84-%EA%B2%B0%EC%A0%95%ED%95%98/
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "tenant_code")
  private String tenantCode;

  @Column(name = "app_code")
  private String appCode;

  @Column(name = "excel_type")
  private String excelType;

  @Lob
  @Column(name="excel_file")
  private File excelFile;

  @Embedded
  private Committed committed;

  public ExcelFile() {
  }

  public ExcelFile(String tenantCode, String appCode, String excelType, File excelFile, String createdBy) throws IOException {
    this.tenantCode = tenantCode;
    this.appCode = appCode;
    this.excelType = excelType;
    this.excelFile = excelFile;
    this.committed = new Committed(new Date(), createdBy);
  }

  public String getLinkUrl(Environment environment) {
    return environment.getProperty("link.url") + "/"  + this.getId();
  }

  public Long getId() {
    return id;
  }

  public String getTenantCode() {
    return tenantCode;
  }

  public String getAppCode() {
    return appCode;
  }

  public String getExcelType() {
    return excelType;
  }

  public File getExcelFile() {
    return excelFile;
  }

  public Committed getCommitted() {
    return committed;
  }
}
