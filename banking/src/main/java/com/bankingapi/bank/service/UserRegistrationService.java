package com.bankingapi.bank.service;

import java.util.List;


import com.bankingapi.bank.dto.RegisterAccountRequestDto;
import com.bankingapi.bank.dto.RegisterAccountResponseDto;


public interface UserRegistrationService {
	public List<RegisterAccountResponseDto> createAccount(RegisterAccountRequestDto registerAccountRequestDto);

	
}
