package com.bettercode.connect.engine.mapper;

public class Account {

  private String id;
  private String transactionTime;
  private String reason;
  private String recipient;
  private String forwardingAmount;
  private String receivedAmount;
  private String balance;
  private String memo;
  private String processingPoint;
  private String sortation;

  public Account() {
  }

  public Account(String id, String transactionTime, String reason, String recipient,
                 String forwardingAmount, String receivedAmount, String balance,
                 String memo, String processingPoint, String sortation) {
    this.id = id;
    this.transactionTime = transactionTime;
    this.reason = reason;
    this.recipient = recipient;
    this.forwardingAmount = forwardingAmount;
    this.receivedAmount = receivedAmount;
    this.balance = balance;
    this.memo = memo;
    this.processingPoint = processingPoint;
    this.sortation = sortation;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(String transactionTime) {
    this.transactionTime = transactionTime;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public String getForwardingAmount() {
    return forwardingAmount;
  }

  public void setForwardingAmount(String forwardingAmount) {
    this.forwardingAmount = forwardingAmount;
  }

  public String getReceivedAmount() {
    return receivedAmount;
  }

  public void setReceivedAmount(String receivedAmount) {
    this.receivedAmount = receivedAmount;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getProcessingPoint() {
    return processingPoint;
  }

  public void setProcessingPoint(String processingPoint) {
    this.processingPoint = processingPoint;
  }

  public String getSortation() {
    return sortation;
  }

  public void setSortation(String sortation) {
    this.sortation = sortation;
  }

  @Override
  public String toString() {
    return "{"
        + "\"id\":\"" + id + "\""
        + ", \"transactionTime\":\"" + transactionTime + "\""
        + ", \"reason\":\"" + reason + "\""
        + ", \"recipient\":\"" + recipient + "\""
        + ", \"forwardingAmount\":\"" + forwardingAmount + "\""
        + ", \"receivedAmount\":\"" + receivedAmount + "\""
        + ", \"balance\":\"" + balance + "\""
        + ", \"memo\":\"" + memo + "\""
        + ", \"processingPoint\":\"" + processingPoint + "\""
        + ", \"sortation\":\"" + sortation + "\""
        + "}";
  }
}
