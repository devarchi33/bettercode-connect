package com.bettercode.connect.entity;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table
@Entity(name = "bank_account_record")
public class BankAccountRecord {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "transaction_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date transactionTime;

  @Column(name = "briefs")
  private String briefs;

  @Column(name = "payee")
  private String payee;

  @Embedded
  private AccountAmount accountAmount;

  @Column(name = "memo")
  private String memo;

  @Column(name = "transaction_point")
  private String transactionPoint;

  @Embedded
  private Committed committed;

  @ManyToOne
  @JoinColumnsOrFormulas(value = {
      @JoinColumnOrFormula(column = @JoinColumn(name = "year", referencedColumnName = "year")),
      @JoinColumnOrFormula(column = @JoinColumn(name = "quater", referencedColumnName = "quater")),
  })
  private BankAccountQuaterRecord bankAccountQuaterRecord;

  public BankAccountQuaterRecord getBankAccountQuaterRecord() {
    return bankAccountQuaterRecord;
  }

  public void setBankAccountQuaterRecord(BankAccountQuaterRecord bankAccountQuaterRecord) {
    this.bankAccountQuaterRecord = bankAccountQuaterRecord;
  }

  public BankAccountRecord() {
  }

  public BankAccountRecord(Long id, Date transactionTime, String briefs, String payee, BigDecimal withdrawAmount,
                           BigDecimal depositAmount, BigDecimal balance, String memo, String transactionPoint,
                           String createdBy) {
    this.id = id;
    this.transactionTime = transactionTime;
    this.briefs = briefs;
    this.payee = payee;
    this.accountAmount = new AccountAmount(withdrawAmount, depositAmount, balance);
    this.memo = memo;
    this.transactionPoint = transactionPoint;
    this.committed = new Committed(new Date(), createdBy);
  }

  public Long getId() {
    return id;
  }

  public Date getTransactionTime() {
    return transactionTime;
  }

  public String getBriefs() {
    return briefs;
  }

  public String getPayee() {
    return payee;
  }

  public AccountAmount getAccountAmount() {
    return accountAmount;
  }

  public String getMemo() {
    return memo;
  }

  public String getTransactionPoint() {
    return transactionPoint;
  }

  public Committed getCommitted() {
    return committed;
  }
}
