package com.bettercode.connect.service.impl;

import com.bettercode.connect.entity.BankAccount;
import com.bettercode.connect.entity.BankAccountRecord;
import com.bettercode.connect.entity.QuaterBankAccountRecord;
import com.bettercode.connect.repository.IBankAccountRepository;
import com.bettercode.connect.service.IBankAccountService;
import com.bettercode.connect.service.dto.CreatingBankAccountRecord;
import com.bettercode.connect.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    QuaterBankAccountRecord newQuaterBankAccountRecord = new QuaterBankAccountRecord(year, quater, createdBy);
    creatingBankAccountRecords.forEach(creatingBankAccountRecord ->
        newQuaterBankAccountRecord.addBankAccountRecord(
            new BankAccountRecord(
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

    // ConcurrentModificationException - https://m.blog.naver.com/PostView.nhn?blogId=tmondev&logNo=220393974518&proxyReferer=https%3A%2F%2Fwww.google.com%2F
    List<QuaterBankAccountRecord> newQuaterBankAccountRecords = bankAccount.getBankAccountQuaterRecords().stream()
        .filter(quaterBankAccountRecord -> !quaterBankAccountRecord.isAlreadyExists(newQuaterBankAccountRecord))
        .collect(Collectors.toList());

    if(newQuaterBankAccountRecords.size() != bankAccount.getBankAccountQuaterRecords().size()) {
      bankAccount.getBankAccountQuaterRecords().clear();
      newQuaterBankAccountRecords.add(newQuaterBankAccountRecord);
      newQuaterBankAccountRecords.forEach(bankAccount::addQuaterBankAccountRecord);
    } else {
      bankAccount.addQuaterBankAccountRecord(newQuaterBankAccountRecord);
    }

    bankAccountRepository.save(bankAccount);
  }
}