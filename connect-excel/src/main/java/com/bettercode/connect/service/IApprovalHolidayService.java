package com.bettercode.connect.service;

import com.bettercode.connect.service.dto.CreatingApprovalHoliday;

import java.text.ParseException;

public interface IApprovalHolidayService {

  Long createApprovalHoliday(CreatingApprovalHoliday creatingHolidayApproval) throws ParseException;

  Long approveHoliday(Long id, Boolean isApprove, String modifyBy);
}
