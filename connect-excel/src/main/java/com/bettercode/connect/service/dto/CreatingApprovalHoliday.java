package com.bettercode.connect.service.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CreatingApprovalHoliday {

  // not null vs not empty vs not blank -> https://developerhjg.tistory.com/160
  @NotBlank
  private String applicant;
  private Date holidayStartAt;
  private Date holidayEndAt;
  @NotBlank
  private String reason;
  @NotBlank
  private String approver;

  public CreatingApprovalHoliday() {
  }

  public CreatingApprovalHoliday(String applicant, Date holidayStartAt, Date holidayEndAt, String reason, String approver) {
    this.applicant = applicant;
    this.holidayStartAt = holidayStartAt;
    this.holidayEndAt = holidayEndAt;
    this.reason = reason;
    this.approver = approver;
  }

  public String getApplicant() {
    return applicant;
  }

  public Date getHolidayStartAt() {
    return holidayStartAt;
  }

  public Date getHolidayEndAt() {
    return holidayEndAt;
  }

  public String getReason() {
    return reason;
  }

  public String getApprover() {
    return approver;
  }
}
