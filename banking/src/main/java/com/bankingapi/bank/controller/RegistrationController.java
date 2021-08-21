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
import com.bankingapi.bank.dto.UserLoginResponseDto;
import com.bankingapi.bank.service.AccountHistroyService;
import com.bankingapi.bank.service.UserRegistrationService;
@RestController
@Validated
public class RegistrationController {

	@Autowired
	UserRegistrationService createAccountService;
	
	@Autowired
	AccountHistroyService accountHistroyService;
	
	@RequestMapping(path = "api/user/createAccount",method = RequestMethod.POST)
	public ResponseEntity<UserLoginResponseDto>createAccount(@RequestBody RegisterAccountRequestDto registerAccountRequestDto) {
		
		return ResponseEntity.ok(createAccountService.createAccount(registerAccountRequestDto));

	}
	
}
