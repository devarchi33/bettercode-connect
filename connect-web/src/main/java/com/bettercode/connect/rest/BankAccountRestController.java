package com.bettercode.connect.rest;


import com.bettercode.connect.service.IBankAccountService;
import com.bettercode.connect.service.dto.CreatingBankAccountRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank-account")
public class BankAccountRestController {

  private Environment env;

  @Autowired
  public void setEnv(Environment env) {
    this.env = env;
  }

  private IBankAccountService bankAccountService;

  @Autowired
  public void setBankAccountService(IBankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  @PutMapping
  public ResponseEntity<Void> upload(@RequestParam("tenantCode") String tenantCode,
                                     @RequestParam("accountNo") String accountNo,
                                     @RequestParam("year") Integer year,
                                     @RequestParam("quater") String quater,
                                     @RequestBody List<CreatingBankAccountRecord> creatingBankAccountRecords,
                                     @RequestParam("createdBy") String createdBy) {

    creatingBankAccountRecords.forEach(creatingBankAccountRecord ->
        System.out.printf("CREATING_BANK_ACCOUNT: %s", creatingBankAccountRecord.toString()));

    bankAccountService.createBankAccountRecord(accountNo, year, quater, creatingBankAccountRecords, createdBy);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}