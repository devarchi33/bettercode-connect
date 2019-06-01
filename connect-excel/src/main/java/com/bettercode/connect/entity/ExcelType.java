package com.bettercode.connect.entity;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity(name = "upload_excel_type")
@IdClass(ExcelTypeId.class)
public class ExcelType {

  @Id
  @Column(name = "tenant_code", length = 60)
  private String tenantCode;

  @Id
  @Column(name = "app_code", length = 60)
  private String appCode;

  @Id
  @Column(name = "excel_type", length = 30)
  private String excelType;

  @Embedded
  private Committed committed;

  public ExcelType() {
  }

  public ExcelType(String tenantCode, String appCode, String excelType, String createdBy) {
    this.tenantCode = tenantCode;
    this.appCode = appCode;
    this.excelType = excelType;
    this.committed = new Committed(new Date(), createdBy);
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

  public Committed getCommitted() {
    return committed;
  }
}
