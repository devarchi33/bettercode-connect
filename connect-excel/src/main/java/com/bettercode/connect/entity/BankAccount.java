package com.bettercode.connect.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Table
@Entity(name = "bank_account")
public class BankAccount {

  @Id
  @Column(name = "account_no")
  private String accountNo;

  @Column(name = "balance")
  private BigDecimal balance;

  @Column(name = "withdraw_amount")
  private BigDecimal withdrawAmount;

  @Embedded
  private Committed committed;

  @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private Set<QuaterBankAccountRecord> bankAccountQuaterRecords = new HashSet<>();

  public void addQuaterBankAccountRecord(QuaterBankAccountRecord bankAccountRecord) {
    this.bankAccountQuaterRecords.add(bankAccountRecord);

    if(bankAccountRecord.getBankAccount() != this) {
      bankAccountRecord.setBankAccount(this);
    }
  }

  public Set<QuaterBankAccountRecord> getBankAccountQuaterRecords() {
    return bankAccountQuaterRecords;
  }

  public BankAccount() {
  }

  public BankAccount(String accountNo, String createdBy) {
    this.accountNo = accountNo;
    this.balance = new BigDecimal(0);
    this.withdrawAmount = new BigDecimal(0);
    this.committed = new Committed(new Date(), createdBy);
  }

  public BankAccount(String accountNo, BigDecimal balance, BigDecimal withdrawAmount, String createdBy) {
    this.accountNo = accountNo;
    this.balance = balance;
    this.withdrawAmount = withdrawAmount;
    this.committed = new Committed(new Date(), createdBy);
  }

  public String getAccountNo() {
    return accountNo;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public BigDecimal getWithdrawAmount() {
    return withdrawAmount;
  }

  public Committed getCommitted() {
    return committed;
  }
}
