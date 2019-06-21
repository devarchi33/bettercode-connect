package com.bettercode.connect.query.dto;

import java.util.ArrayList;
import java.util.List;

public class CreatedBankAccountQuaterRecord {

  private Integer year;
  private String quater;
  private List<CreatedBankAccountRecord> createdBankAccountRecords = new ArrayList<>();

  public void addBankAccountRecord(CreatedBankAccountRecord createdBankAccountRecord) {
    this.createdBankAccountRecords.add(createdBankAccountRecord);
  }

  public CreatedBankAccountQuaterRecord() {
  }

  public CreatedBankAccountQuaterRecord(Integer year, String quater) {
    this.year = year;
    this.quater = quater;
  }

  public Integer getYear() {
    return year;
  }

  public String getQuater() {
    return quater;
  }

  public List<CreatedBankAccountRecord> getCreatedBankAccountRecords() {
    return createdBankAccountRecords;
  }
}
