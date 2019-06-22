package com.bettercode.connect.query.impl;

import com.bettercode.connect.entity.BankAccount;
import com.bettercode.connect.entity.QuaterBankAccountRecord;
import com.bettercode.connect.query.IBankAccountRecordQuery;
import com.bettercode.connect.query.dto.CreatedBankAccount;
import com.bettercode.connect.query.dto.CreatedBankAccountQuaterRecord;
import com.bettercode.connect.query.dto.CreatedBankAccountRecord;
import com.bettercode.connect.repository.IBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountRecordQueryJpaImpl implements IBankAccountRecordQuery {

  private IBankAccountRepository bankAccountRepository;

  @Autowired
  public void setBankAccountRepository(IBankAccountRepository bankAccountRepository) {
    this.bankAccountRepository = bankAccountRepository;
  }

  @Override
  public CreatedBankAccount queryForBankAccount(String accountNo, Integer year, String quater) {
    Optional<BankAccount> foundBankAccount = bankAccountRepository.findById(accountNo);
    if(foundBankAccount.isPresent()) {
      BankAccount bankAccount = foundBankAccount.get();
      QuaterBankAccountRecord foundBankAccountQuaterRecord = bankAccount.getBankAccountQuaterRecords().stream()
          .filter(bankAccountQuaterRecord -> bankAccountQuaterRecord.getYear().equals(year))
          .filter(bankAccountQuaterRecord -> bankAccountQuaterRecord.getQuater().equals(quater))
          .collect(Collectors.toList())
          .get(0);

      CreatedBankAccountQuaterRecord createdBankAccountQuaterRecord = new CreatedBankAccountQuaterRecord(year, quater);

      foundBankAccountQuaterRecord.getBankAccountRecords().forEach(bankAccountRecord -> {
        createdBankAccountQuaterRecord.addBankAccountRecord(
            new CreatedBankAccountRecord(
                bankAccountRecord.getId().intValue(),
                bankAccountRecord.getAccountAmount().getBalance().intValue(),
                bankAccountRecord.getAccountAmount().getDepositAmount().intValue(),
                bankAccountRecord.getAccountAmount().getWithdrawAmount().intValue(),
                bankAccountRecord.getBriefs(),
                bankAccountRecord.getMemo(),
                bankAccountRecord.getPayee(),
                bankAccountRecord.getTransactionPoint(),
                bankAccountRecord.getTransactionTime().toString()
            ));
      });

      return new CreatedBankAccount(bankAccount.getAccountNo(), createdBankAccountQuaterRecord);
    } else {
      return new CreatedBankAccount();
    }
  }
}
