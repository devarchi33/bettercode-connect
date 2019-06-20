package com.bettercode.connect.repository;

import com.bettercode.connect.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankAccountRepository extends JpaRepository<BankAccount, String> {
}
