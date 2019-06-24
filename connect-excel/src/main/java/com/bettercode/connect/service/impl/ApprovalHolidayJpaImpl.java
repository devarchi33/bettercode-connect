package com.bettercode.connect.service.impl;

import com.bettercode.connect.entity.ApprovalHoliday;
import com.bettercode.connect.repository.IApprovalHolidayRepository;
import com.bettercode.connect.service.IApprovalHolidayService;
import com.bettercode.connect.service.dto.CreatingApprovalHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ApprovalHolidayJpaImpl implements IApprovalHolidayService {

  private IApprovalHolidayRepository approvalHolidayRepository;

  @Autowired
  public void setApprovalHolidayRepository(IApprovalHolidayRepository approvalHolidayRepository) {
    this.approvalHolidayRepository = approvalHolidayRepository;
  }

  @Override
  public Long createHolidayApproval(CreatingApprovalHoliday creatingHolidayApproval) throws ParseException {
    ApprovalHoliday approvalHoliday = new ApprovalHoliday(
        creatingHolidayApproval.getApplicant(),
        creatingHolidayApproval.getHolidayStartAt(),
        creatingHolidayApproval.getHolidayEndAt(),
        creatingHolidayApproval.getReason(),
        creatingHolidayApproval.getApprover(),
        creatingHolidayApproval.getApplicant()
    );

    if(!approvalHoliday.isValidHolidayDuration()) {
      throw new RuntimeException("휴가는 15일을 초과할 수 없습니다.");
    }

    return approvalHolidayRepository.save(approvalHoliday).getId();
  }
}
