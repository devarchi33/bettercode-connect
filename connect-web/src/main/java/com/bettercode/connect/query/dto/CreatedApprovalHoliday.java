package com.bettercode.connect.query.dto;

public class CreatedApprovalHoliday {

  private Long id;
  private String applicant;
  private String approver;
  private Boolean isApprove;

  public CreatedApprovalHoliday() {
  }

  public CreatedApprovalHoliday(Long id, String applicant, String approver, Boolean isApprove) {
    this.id = id;
    this.applicant = applicant;
    this.approver = approver;
    this.isApprove = isApprove;
  }

  public Long getId() {
    return id;
  }

  public String getApplicant() {
    return applicant;
  }

  public String getApprover() {
    return approver;
  }

  public Boolean getApprove() {
    return isApprove;
  }
}
