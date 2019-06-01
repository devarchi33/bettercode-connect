package com.bettercode.connect.entity;

import java.io.Serializable;
import java.util.Objects;

public class ExcelTypeId implements Serializable {

  private String tenantCode;
  private String appCode;
  private String excelType;

  public ExcelTypeId() {
  }

  public ExcelTypeId(String tenantCode, String appCode, String excelType) {
    this.tenantCode = tenantCode;
    this.appCode = appCode;
    this.excelType = excelType;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ExcelTypeId)) return false;
    ExcelTypeId that = (ExcelTypeId) o;
    return Objects.equals(tenantCode, that.tenantCode) &&
        Objects.equals(appCode, that.appCode) &&
        Objects.equals(excelType, that.excelType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tenantCode, appCode, excelType);
  }
}
