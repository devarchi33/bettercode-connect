package com.bettercode.connect.query.dto;

public class CreatedBankAccount {

  private String accountNo;
  private CreatedBankAccountQuaterRecord createdBankAccountQuaterRecords;

  public CreatedBankAccount() {
  }

  public CreatedBankAccount(String accountNo, CreatedBankAccountQuaterRecord createdBankAccountQuaterRecords) {
    this.accountNo = accountNo;
    this.createdBankAccountQuaterRecords = createdBankAccountQuaterRecords;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public CreatedBankAccountQuaterRecord getCreatedBankAccountQuaterRecords() {
    return createdBankAccountQuaterRecords;
  }
}
