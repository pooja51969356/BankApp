package com.bankingapi.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapi.bank.dto.RegisterAccountRequestDto;
import com.bankingapi.bank.dto.RegisterAccountResponseDto;
import com.bankingapi.bank.service.AccountHistroyService;
import com.bankingapi.bank.service.UserRegistrationService;
@RestController
@Validated
public class Controller {

	@Autowired
	UserRegistrationService createAccountService;
	
	@Autowired
	AccountHistroyService accountHistroyService;
	
	@RequestMapping(path = "api/product/createAccount",method = RequestMethod.POST)
	public ResponseEntity<List<RegisterAccountResponseDto>> createAccount(@RequestBody RegisterAccountRequestDto registerAccountRequestDto) {
		
		return ResponseEntity.ok(createAccountService.createAccount(registerAccountRequestDto));

	}
	
	/*
	 * @RequestMapping(path = "api/order/accountHistory/account/{account_no}",method
	 * = RequestMethod.GET) public ResponseEntity<List<AccountHistoryResponseDto>>
	 * createAccountDemo(@PathVariable String account_no) {
	 * 
	 * return
	 * ResponseEntity.ok(accountHistroyService.getAccountHistory(account_no));
	 * 
	 * }
	 */
}
