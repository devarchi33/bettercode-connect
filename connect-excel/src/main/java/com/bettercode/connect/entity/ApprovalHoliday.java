package com.bettercode.connect.entity;

import com.bettercode.connect.utils.DateUtil;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Table
@Entity(name = "approval_holiday")
public class ApprovalHoliday {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "applicant")
  private String applicant;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "holiday_start_at")
  private Date holidayStartAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "holiday_end_at")
  private Date holidayEndAt;

  @Column(name = "reason")
  private String reason;

  @Column(name = "approver")
  private String approver;

  @Column(name = "is_approve", columnDefinition = "tinyint(1)")
  private Boolean isApprove;

  @Embedded
  private Committed committed;

  public ApprovalHoliday() {
  }

  public ApprovalHoliday(String applicant, Date holidayStartAt, Date holidayEndAt, String reason, String approver, String createdBy) {
    this.applicant = applicant;
    this.holidayStartAt = holidayStartAt;
    this.holidayEndAt = holidayEndAt;
    this.reason = reason;
    this.approver = approver;
    this.isApprove = false;
    this.committed = new Committed(new Date(), createdBy);
  }

  public Boolean isValidHolidayDuration() throws ParseException {
    return DateUtil.calculateDurationByDate(this.holidayStartAt, this.holidayEndAt) <= 14;
  }

  public Long getId() {
    return id;
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

  public Boolean getApprove() {
    return isApprove;
  }

  public Committed getCommitted() {
    return committed;
  }
}
