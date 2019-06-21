package com.bettercode.connect.query.dto;

public class CreatedBankAccountRecord {

  private Integer id;
  private Integer balance;
  private Integer depositAmount;
  private Integer withdrawAmount;
  private String briefs;
  private String memo;
  private String payee;
  private String transactionPoint;
  private String transactionTime;

  public CreatedBankAccountRecord() {
  }

  public CreatedBankAccountRecord(Integer id, Integer balance, Integer depositAmount, Integer withdrawAmount,
                                  String briefs, String memo, String payee, String transactionPoint, String transactionTime) {
    this.id = id;
    this.balance = balance;
    this.depositAmount = depositAmount;
    this.withdrawAmount = withdrawAmount;
    this.briefs = briefs;
    this.memo = memo;
    this.payee = payee;
    this.transactionPoint = transactionPoint;
    this.transactionTime = transactionTime;
  }

  public Integer getId() {
    return id;
  }

  public Integer getBalance() {
    return balance;
  }

  public Integer getDepositAmount() {
    return depositAmount;
  }

  public Integer getWithdrawAmount() {
    return withdrawAmount;
  }

  public String getBriefs() {
    return briefs;
  }

  public String getMemo() {
    return memo;
  }

  public String getPayee() {
    return payee;
  }

  public String getTransactionPoint() {
    return transactionPoint;
  }

  public String getTransactionTime() {
    return transactionTime;
  }
}
