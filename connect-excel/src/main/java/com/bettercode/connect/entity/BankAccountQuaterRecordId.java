package com.bettercode.connect.entity;

import java.io.Serializable;
import java.util.Objects;

public class BankAccountQuaterRecordId implements Serializable {

  private Integer year;
  private String quater;

  public BankAccountQuaterRecordId() {
  }

  public BankAccountQuaterRecordId(Integer year, String quater) {
    this.year = year;
    this.quater = quater;
  }

  public Integer getYear() {
    return year;
  }

  public String getQuater() {
    return quater;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BankAccountQuaterRecordId)) return false;
    BankAccountQuaterRecordId that = (BankAccountQuaterRecordId) o;
    return Objects.equals(year, that.year) &&
        Objects.equals(quater, that.quater);
  }

  @Override
  public int hashCode() {
    return Objects.hash(year, quater);
  }
}
