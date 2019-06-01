package com.bettercode.connect.excel.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
public class Committed {

  @Column(name = "created_by", length = 30)
  private String createdBy;

  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @Column(name = "modified_by", length = 30)
  private String modifiedBy;

  @Temporal(TemporalType.TIMESTAMP)
  private Date modified;

  public Committed() {
  }

  public Committed(Date created, String createdBy) {
    this.createdBy = createdBy;
    this.created = created;
    this.modifiedBy = createdBy;
    this.modified = created;
  }

  public void update(String updatedBy) {
    this.modifiedBy = updatedBy;
    this.modified = new Date();
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public Date getCreated() {
    return created;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public Date getModified() {
    return modified;
  }

  @Override
  public String toString() {
    return "{"
        + "\"createdBy\":\"" + createdBy + "\""
        + ", \"created\":" + created
        + ", \"modifiedBy\":\"" + modifiedBy + "\""
        + ", \"modified\":" + modified
        + "}";
  }
}
