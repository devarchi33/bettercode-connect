package com.bettercode.connect.service.impl;

import com.bettercode.connect.entity.ApprovalHoliday;
import com.bettercode.connect.repository.IApprovalHolidayRepository;
import com.bettercode.connect.service.IApprovalHolidayService;
import com.bettercode.connect.service.dto.CreatingApprovalHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class ApprovalHolidayJpaImpl implements IApprovalHolidayService {

  private IApprovalHolidayRepository approvalHolidayRepository;

  @Autowired
  public void setApprovalHolidayRepository(IApprovalHolidayRepository approvalHolidayRepository) {
    this.approvalHolidayRepository = approvalHolidayRepository;
  }

  @Override
  public Long createApprovalHoliday(CreatingApprovalHoliday creatingHolidayApproval) throws ParseException {
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

  @Override
  public Long approveHoliday(Long id, Boolean isApprove, String modifyBy) {
    Optional<ApprovalHoliday> optionalApprovalHoliday = approvalHolidayRepository.findById(id);
    if(optionalApprovalHoliday.isPresent()) {
      ApprovalHoliday approvalHoliday = optionalApprovalHoliday.get();
      approvalHoliday.setApprove(isApprove);
      approvalHoliday.getCommitted().update(modifyBy);
      return approvalHolidayRepository.save(approvalHoliday).getId();
    } else {
      throw new RuntimeException("존재하지 않는 승인번호 입니다. 확인후 다시 신청해주세요.");
    }
  }
}
