package com.bankingapi.bank.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankingapi.bank.entity.*;
import com.bankingapi.bank.entity.AccountEntity;

public interface AccountHistoryRepositroy extends JpaRepository<AccountEntity, String>{


	public AccountEntity findByAccountNumber(BigInteger fromAccount);
	
	
//	public BigInteger findAccountNumberByCustomerId(String customer_id);
	
	
}
