package com.bettercode.connect.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class AccountAmount {

  @Column(name = "withdraw_amount")
  private BigDecimal withdrawAmount;

  @Column(name = "deposit_amount")
  private BigDecimal depositAmount;

  @Column(name = "balance")
  private BigDecimal balance;

  public AccountAmount() {
  }

  public AccountAmount(BigDecimal withdrawAmount, BigDecimal depositAmount, BigDecimal balance) {
    this.withdrawAmount = withdrawAmount;
    this.depositAmount = depositAmount;
    this.balance = balance;
  }

  public BigDecimal getWithdrawAmount() {
    return withdrawAmount;
  }

  public BigDecimal getDepositAmount() {
    return depositAmount;
  }

  public BigDecimal getBalance() {
    return balance;
  }
}
