package com.bettercode.connect.entity;

import com.bettercode.connect.utils.MultipartFileUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Table
@Entity(name = "upload_excel_file_temp")
public class WorkExcelFile {

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

  @Column(name = "row_mapper_name")
  private String rowMapperName;

  @Lob
  @Column(name="excel_file")
  private byte[] excelFile;

  @Embedded
  private Committed committed;

  public WorkExcelFile() {
  }

  public WorkExcelFile(String tenantCode, String appCode, String excelType, MultipartFile excelFile, String createdBy) throws IOException {
    this.tenantCode = tenantCode;
    this.appCode = appCode;
    this.excelType = excelType;
    this.rowMapperName = createRowMapperName();
    this.excelFile = excelFile.getBytes();
    this.committed = new Committed(new Date(), createdBy);
  }

  public String getLinkUrl(Environment environment) {
    return environment.getProperty("link.url") + "/"  + this.getId();
  }

  public File getOriginalFile() throws IOException {
    return MultipartFileUtils.writeBytesToFile(excelFile, "./" + tenantCode + "_" + appCode + "_" + excelType + "_temp.xlsx");
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

  public String getRowMapperName() {
    return rowMapperName;
  }

  private String  createRowMapperName() {
    return excelType.substring(0, 1).toLowerCase() + excelType.substring(1)
        + "ExcelItemProcessor";
  }

  public byte[] getExcelFile() {
    return excelFile;
  }

  public Committed getCommitted() {
    return committed;
  }
}
