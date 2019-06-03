package com.bettercode.connect.engine.mapper;

import java.util.ArrayList;
import java.util.List;

public class AccountRecord {

  private String accountNo;
  private String firstLoanDate;
  private String totalBalance;
  private String loanMaturityDate;
  private String accountName;
  private String loanLimit;
  private String allowanceAmount;
  private String loanInterestRate;
  private String widthdrawalSum;
  private String depositSum;
  private String checkPeriod;
  private List<Account> bettercodeConnectAccountRecords = new ArrayList<>();

  public AccountRecord() {
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getFirstLoanDate() {
    return firstLoanDate;
  }

  public void setFirstLoanDate(String firstLoanDate) {
    this.firstLoanDate = firstLoanDate;
  }

  public String getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(String totalBalance) {
    this.totalBalance = totalBalance;
  }

  public String getLoanMaturityDate() {
    return loanMaturityDate;
  }

  public void setLoanMaturityDate(String loanMaturityDate) {
    this.loanMaturityDate = loanMaturityDate;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getLoanLimit() {
    return loanLimit;
  }

  public void setLoanLimit(String loanLimit) {
    this.loanLimit = loanLimit;
  }

  public String getAllowanceAmount() {
    return allowanceAmount;
  }

  public void setAllowanceAmount(String allowanceAmount) {
    this.allowanceAmount = allowanceAmount;
  }

  public String getLoanInterestRate() {
    return loanInterestRate;
  }

  public void setLoanInterestRate(String loanInterestRate) {
    this.loanInterestRate = loanInterestRate;
  }

  public String getWidthdrawalSum() {
    return widthdrawalSum;
  }

  public void setWidthdrawalSum(String widthdrawalSum) {
    this.widthdrawalSum = widthdrawalSum;
  }

  public String getDepositSum() {
    return depositSum;
  }

  public void setDepositSum(String depositSum) {
    this.depositSum = depositSum;
  }

  public String getCheckPeriod() {
    return checkPeriod;
  }

  public void setCheckPeriod(String checkPeriod) {
    this.checkPeriod = checkPeriod;
  }

  public void addBettercodeConnectAccountRecord(Object record) {
    this.bettercodeConnectAccountRecords.add((Account) record);
  }

  @Override
  public String toString() {
    return "{"
        + "\"accountNo\":\"" + accountNo + "\""
        + ", \"firstLoanDate\":\"" + firstLoanDate + "\""
        + ", \"totalBalance\":\"" + totalBalance + "\""
        + ", \"loanMaturityDate\":\"" + loanMaturityDate + "\""
        + ", \"accountName\":\"" + accountName + "\""
        + ", \"loanLimit\":\"" + loanLimit + "\""
        + ", \"allowanceAmount\":\"" + allowanceAmount + "\""
        + ", \"loanInterestRate\":\"" + loanInterestRate + "\""
        + ", \"widthdrawalSum\":\"" + widthdrawalSum + "\""
        + ", \"depositSum\":\"" + depositSum + "\""
        + ", \"checkPeriod\":\"" + checkPeriod + "\""
        + ", \"bettercodeConnectAccountRecords\":" + bettercodeConnectAccountRecords.toString()
        + "}";
  }
}
