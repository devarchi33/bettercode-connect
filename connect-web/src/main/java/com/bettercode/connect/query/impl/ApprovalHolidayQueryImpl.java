package com.bettercode.connect.query.impl;

import com.bettercode.connect.entity.ApprovalHoliday;
import com.bettercode.connect.query.IApprovalHolidayQuery;
import com.bettercode.connect.query.dto.CreatedApprovalHoliday;
import com.bettercode.connect.repository.IApprovalHolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApprovalHolidayQueryImpl implements IApprovalHolidayQuery {

  private IApprovalHolidayRepository approvalHolidayRepository;

  @Autowired
  public void setApprovalHolidayRepository(IApprovalHolidayRepository approvalHolidayRepository) {
    this.approvalHolidayRepository = approvalHolidayRepository;
  }

  @Override
  public CreatedApprovalHoliday findApprovalHolidayById(Long id) {
    Optional<ApprovalHoliday> approvalHoliday = approvalHolidayRepository.findById(id);
    return approvalHoliday.map(holiday -> new CreatedApprovalHoliday(
        holiday.getId(),
        holiday.getApplicant(),
        holiday.getApprover(),
        holiday.getApprove()
    )).orElseGet(CreatedApprovalHoliday::new);
  }
}
