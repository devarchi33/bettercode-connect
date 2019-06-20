package com.bettercode.connect.rest;


import com.bettercode.connect.dto.CreatingBankAccountRecord;
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

  @PutMapping
  public ResponseEntity<Void> upload(@RequestParam("tenantCode") String tenantCode,
                                     @RequestBody List<CreatingBankAccountRecord> creatingBankAccountRecords) {

    creatingBankAccountRecords.forEach(creatingBankAccountRecord ->
        System.out.printf("CREATING_BANK_ACCOUNT: %s", creatingBankAccountRecord.toString()));

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}