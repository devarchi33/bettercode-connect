package com.bettercode.connect.entity;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity(name = "approval_holiday")
public class HolidayAppoval {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

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

  public HolidayAppoval() {
  }

  public HolidayAppoval(Date holidayStartAt, Date holidayEndAt, String reason, String approver, Boolean isApprove, String createdBy) {
    this.holidayStartAt = holidayStartAt;
    this.holidayEndAt = holidayEndAt;
    this.reason = reason;
    this.approver = approver;
    this.isApprove = isApprove;
    this.committed = new Committed(new Date(), createdBy);
  }

  public Long getId() {
    return id;
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
