package com.bettercode.connect.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
  private List<BankAccountQuaterRecord> bankAccountQuaterRecords = new ArrayList<>();

  public void addBankAccountRecord(BankAccountQuaterRecord bankAccountRecord) {
    this.bankAccountQuaterRecords.add(bankAccountRecord);

    if(bankAccountRecord.getBankAccount() != this) {
      bankAccountRecord.setBankAccount(this);
    }
  }

  public List<BankAccountQuaterRecord> getBankAccountQuaterRecords() {
    return bankAccountQuaterRecords;
  }

  public BankAccount() {
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
