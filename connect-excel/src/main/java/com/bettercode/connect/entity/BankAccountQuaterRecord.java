package com.bettercode.connect.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Entity(name = "bank_account_quater_record")
@IdClass(BankAccountQuaterRecordId.class)
public class BankAccountQuaterRecord {

  @Id
  @Column(name = "year")
  private Integer year;

  @Id
  @Column(name = "quater")
  private String quater;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "withdrawAmount", column = @Column(name = "total_quater_withdraw_amount")),
      @AttributeOverride(name = "depositAmount", column = @Column(name = "total_quater_deposit_amount")),
      @AttributeOverride(name = "balance", column = @Column(name = "balance"))
  })
  private AccountAmount totalQuaterAccountAmount;

  @Embedded
  private Committed committed;

  @ManyToOne
  @JoinColumn(name = "account_no")
  private BankAccount bankAccount;

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  @OneToMany(mappedBy = "bankAccountQuaterRecord", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<BankAccountRecord> bankAccountRecords = new ArrayList<>();

  public void addBankAccountRecord(BankAccountRecord bankAccountRecord) {
    this.bankAccountRecords.add(bankAccountRecord);

    if(bankAccountRecord.getBankAccountQuaterRecord() != this) {
      bankAccountRecord.setBankAccountQuaterRecord(this);
    }
  }
  public List<BankAccountRecord> getBankAccountRecords() {
    return bankAccountRecords;
  }

  public BankAccountQuaterRecord() {
  }

  public BankAccountQuaterRecord(Integer year, String quater, AccountAmount totalQuaterAccountAmount, String createdBy) {
    this.year = year;
    this.quater = quater;
    this.totalQuaterAccountAmount = totalQuaterAccountAmount;
    this.committed = new Committed(new Date(), createdBy);
  }

  public Integer getYear() {
    return year;
  }

  public String getQuater() {
    return quater;
  }

  public AccountAmount getTotalQuaterAccountAmount() {
    return totalQuaterAccountAmount;
  }

  public Committed getCommitted() {
    return committed;
  }
}
