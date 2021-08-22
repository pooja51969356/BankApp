package com.bankingapi.bank.service;

import com.bankingapi.bank.dto.LoginDetails;
import com.bankingapi.bank.dto.LoginResponseDto;

public interface LoginService {
	
	public LoginResponseDto customerLogin(LoginDetails login);

}
