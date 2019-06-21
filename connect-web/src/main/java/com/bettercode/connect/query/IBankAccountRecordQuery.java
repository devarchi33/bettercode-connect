package com.bettercode.connect.query;

import com.bettercode.connect.query.dto.CreatedBankAccount;

public interface IBankAccountRecordQuery {

  CreatedBankAccount queryForBankAccount(String accountNo, Integer year, String quater);
}
