package com.bettercode.connect.service;

import com.bettercode.connect.service.dto.CreatingBankAccountRecord;

import java.util.List;

public interface IBankAccountService {
  void createBankAccountRecord(String accountNo, Integer year, String quater, List<CreatingBankAccountRecord> creatingBankAccountRecord, String createdBy);
}
