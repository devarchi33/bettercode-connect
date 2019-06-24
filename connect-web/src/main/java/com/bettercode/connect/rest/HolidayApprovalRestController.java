package com.bettercode.connect.rest;


import com.bettercode.connect.service.IApprovalHolidayService;
import com.bettercode.connect.service.dto.CreatingApprovalHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/approval-holiday")
public class HolidayApprovalRestController {

  private IApprovalHolidayService holidayApprovalService;

  @Autowired
  public void setHolidayApprovalService(IApprovalHolidayService holidayApprovalService) {
    this.holidayApprovalService = holidayApprovalService;
  }

  @PutMapping
  public ResponseEntity<Long> createHolidayApproval(@Valid @RequestBody CreatingApprovalHoliday creatingHolidayApproval) throws ParseException {

    return new ResponseEntity<>(
        holidayApprovalService.createHolidayApproval(creatingHolidayApproval),
        HttpStatus.CREATED
    );
  }
}