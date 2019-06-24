package com.bettercode.connect.service;

import com.bettercode.connect.service.dto.CreatingApprovalHoliday;

import java.text.ParseException;

public interface IApprovalHolidayService {

  Long createHolidayApproval(CreatingApprovalHoliday creatingHolidayApproval) throws ParseException;
}
