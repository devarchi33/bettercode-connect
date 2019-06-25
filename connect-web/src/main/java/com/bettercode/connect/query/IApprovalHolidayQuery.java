package com.bettercode.connect.query;

import com.bettercode.connect.query.dto.CreatedApprovalHoliday;

public interface IApprovalHolidayQuery {
  CreatedApprovalHoliday findApprovalHolidayById(Long id);
}
