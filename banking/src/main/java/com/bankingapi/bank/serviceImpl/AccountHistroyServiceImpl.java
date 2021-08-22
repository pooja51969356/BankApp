package com.bankingapi.bank.serviceImpl;


import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapi.bank.repository.AccountHistoryRepositroy;
import com.bankingapi.bank.service.AccountHistroyService;


@Service
public class AccountHistroyServiceImpl implements AccountHistroyService{

	@Autowired
	AccountHistoryRepositroy accountHistoryRepositroy;
	
	@Autowired
	EntityManagerFactory emf;
	
	/*
	 * @Override public List<AccountHistoryResponseDto> getAccountHistory(String
	 * account_no) {
	 * 
	 * List<AccountHistory> accountHistory
	 * =accountHistoryRepositroy.searchAccountHistoryByAccountNumber(new
	 * BigInteger(account_no));
	 * 
	 * accountHistory.forEach( e -> System.out.println(e.toString()));
	 * 
	 * return null;
	 * 
	 * }
	 */
}

