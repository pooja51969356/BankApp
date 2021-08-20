package com.bankingapi.bank.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankingapi.bank.entity.*;
import com.bankingapi.bank.entity.AccountEntity;

public interface AccountHistoryRepositroy extends JpaRepository<AccountEntity, String>{

	@Query(value = "SELECT * FROM  account_details ad "
			+ "LEFT JOIN transaction_details tm on ad.account_number=tm.from_account OR ad.account_number=tm.to_account "
			+ "WHERE "
			+ "ad.account_number =:account_number", nativeQuery = true)
	public List<AccountHistory> searchAccountHistoryByAccountNumber(@Param("account_number") BigInteger account_number);

	public AccountEntity findByAccountNumber(BigInteger fromAccount);
	
}
