package com.bankingapi.bank.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginResponseDto {
	 
	    private String name;
	    private String customerId;
	    private String password;
	    private boolean registrationStatus;
	    private String ResponseMessage;
		
}
