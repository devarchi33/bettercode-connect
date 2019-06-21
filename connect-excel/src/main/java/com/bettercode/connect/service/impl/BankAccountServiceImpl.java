package com.bettercode.connect.service.impl;

import com.bettercode.connect.entity.BankAccount;
import com.bettercode.connect.entity.BankAccountQuaterRecord;
import com.bettercode.connect.entity.BankAccountRecord;
import com.bettercode.connect.repository.IBankAccountRepository;
import com.bettercode.connect.service.IBankAccountService;
import com.bettercode.connect.service.dto.CreatingBankAccountRecord;
import com.bettercode.connect.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.bettercode.connect.utils.NumberUtil.removeThousandUnit;

@Service
public class BankAccountServiceImpl implements IBankAccountService {

  private IBankAccountRepository bankAccountRepository;

  @Autowired
  public void setBankAccountRepository(IBankAccountRepository bankAccountRepository) {
    this.bankAccountRepository = bankAccountRepository;
  }

  @Override
  public void createBankAccountRecord(String accountNo, Integer year, String quater, List<CreatingBankAccountRecord> creatingBankAccountRecords, String createdBy) {
    BankAccount bankAccount;

    Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(accountNo);
    bankAccount = bankAccountOptional.orElseGet(() -> new BankAccount(accountNo, createdBy));

    BankAccountQuaterRecord newBankAccountQuaterRecord = new BankAccountQuaterRecord(year, quater, createdBy);

    creatingBankAccountRecords.forEach(creatingBankAccountRecord ->
        newBankAccountQuaterRecord.addBankAccountRecord(
            new BankAccountRecord(
                creatingBankAccountRecord.getId().longValue(),
                DateUtil.getDateFromString(creatingBankAccountRecord.getTransactionTime()),
                creatingBankAccountRecord.getReason(),
                creatingBankAccountRecord.getRecipient(),
                new BigDecimal(Integer.parseInt(removeThousandUnit(creatingBankAccountRecord.getForwardingAmount()))),
                new BigDecimal(Integer.parseInt(removeThousandUnit(creatingBankAccountRecord.getReceivedAmount()))),
                new BigDecimal(Integer.parseInt(removeThousandUnit(creatingBankAccountRecord.getBalance()))),
                creatingBankAccountRecord.getMemo(),
                creatingBankAccountRecord.getProcessingPoint(),
                createdBy
            )
        ));

    Set<BankAccountQuaterRecord> bankAccountQuaterRecords = bankAccount.getBankAccountQuaterRecords();

    if(bankAccountQuaterRecords.size() > 0) {
      bankAccountQuaterRecords.forEach(bankAccountQuaterRecord -> {
        if (bankAccountQuaterRecord.isAlreadyExists(newBankAccountQuaterRecord)) {
          bankAccountQuaterRecords.remove(bankAccountQuaterRecord);
        }
        bankAccountQuaterRecords.add(newBankAccountQuaterRecord);
      });
      bankAccountQuaterRecords.forEach(bankAccount::addBankAccountQuaterRecord);
    } else {
      bankAccount.addBankAccountQuaterRecord(newBankAccountQuaterRecord);
    }

    bankAccountRepository.save(bankAccount);
  }
}