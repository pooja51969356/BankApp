package com.bankingapi.bank.service;

import java.util.List;


import com.bankingapi.bank.dto.RegisterAccountRequestDto;
import com.bankingapi.bank.dto.RegisterAccountResponseDto;
import com.bankingapi.bank.dto.UserLoginResponseDto;


public interface UserRegistrationService {
	public UserLoginResponseDto createAccount(RegisterAccountRequestDto registerAccountRequestDto);

	
}
