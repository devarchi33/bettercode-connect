package com.bettercode.connect.dto;

public class CreatingBankAccountRecord {

  private String balance;
  // todo forwardingAmount -> withdrawAmount
  private String forwardingAmount;
  private Integer id;
  private String memo;
  // todo processingPoint -> transactionPoint
  private String processingPoint;
  // todo reason -> briefs
  private String reason;
  // todo receivedAmount -> depositAmount
  private String receivedAmount;
  // todo recipient -> payee
  private String recipient;
  private String transactionTime;

  public CreatingBankAccountRecord() {
  }

  public CreatingBankAccountRecord(String balance, String forwardingAmount, Integer id, String memo,
                                   String processingPoint, String reason, String receivedAmount, String recipient,
                                   String transactionTime) {
    this.balance = balance;
    this.forwardingAmount = forwardingAmount;
    this.id = id;
    this.memo = memo;
    this.processingPoint = processingPoint;
    this.reason = reason;
    this.receivedAmount = receivedAmount;
    this.recipient = recipient;
    this.transactionTime = transactionTime;
  }

  public String getBalance() {
    return balance;
  }

  public String getForwardingAmount() {
    return forwardingAmount;
  }

  public Integer getId() {
    return id;
  }

  public String getMemo() {
    return memo;
  }

  public String getProcessingPoint() {
    return processingPoint;
  }

  public String getReason() {
    return reason;
  }

  public String getReceivedAmount() {
    return receivedAmount;
  }

  public String getRecipient() {
    return recipient;
  }

  public String getTransactionTime() {
    return transactionTime;
  }

  @Override
  public String toString() {
    return "{"
        + "\"balance\":\"" + balance + "\""
        + ", \"forwardingAmount\":\"" + forwardingAmount + "\""
        + ", \"id\":\"" + id + "\""
        + ", \"memo\":\"" + memo + "\""
        + ", \"processingPoint\":\"" + processingPoint + "\""
        + ", \"reason\":\"" + reason + "\""
        + ", \"receivedAmount\":\"" + receivedAmount + "\""
        + ", \"recipient\":\"" + recipient + "\""
        + ", \"transactionTime\":\"" + transactionTime + "\""
        + "}";
  }
}
