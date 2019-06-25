package com.bettercode.connect.rest;


import com.bettercode.connect.query.IApprovalHolidayQuery;
import com.bettercode.connect.query.dto.CreatedApprovalHoliday;
import com.bettercode.connect.service.IApprovalHolidayService;
import com.bettercode.connect.service.dto.CreatingApprovalHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/approval-holiday")
public class HolidayApprovalRestController {

  private IApprovalHolidayService approvalHolidayService;

  @Autowired
  public void setApprovalHolidayService(IApprovalHolidayService approvalHolidayService) {
    this.approvalHolidayService = approvalHolidayService;
  }

  private IApprovalHolidayQuery approvalHolidayQuery;

  @Autowired
  public void setApprovalHolidayQuery(IApprovalHolidayQuery approvalHolidayQuery) {
    this.approvalHolidayQuery = approvalHolidayQuery;
  }

  @PutMapping
  public ResponseEntity<Long> createHolidayApproval(@Valid @RequestBody CreatingApprovalHoliday creatingHolidayApproval) throws ParseException {

    return new ResponseEntity<>(
        approvalHolidayService.createApprovalHoliday(creatingHolidayApproval),
        HttpStatus.CREATED
    );
  }

  @GetMapping
  public ResponseEntity<CreatedApprovalHoliday> findApprovalHolidayById(@RequestParam Long id) {
    return new ResponseEntity<>(
        approvalHolidayQuery.findApprovalHolidayById(id),
        HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<Long> modifyingApprovalHoliday(@RequestParam Long id,
                                                       @RequestParam Boolean isApprove,
                                                       @RequestParam String modifyBy) {
    return new ResponseEntity<>(
        approvalHolidayService.approveHoliday(id, isApprove, modifyBy),
        HttpStatus.OK
    );
  }
}