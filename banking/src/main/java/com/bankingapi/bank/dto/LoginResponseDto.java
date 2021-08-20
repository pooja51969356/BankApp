package com.bankingapi.bank.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.bankingapi.bank.entity.AccountEntity;
import com.bankingapi.bank.entity.CustomerEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponseDto {
	   private String customerId;
		private Long  accountNumber;
		private String accountType;
		private String branchAddress;
		private String ifscCode;
		private BigInteger openingBalance;
		private boolean loginStatus;
		private String responseMessage;
		private String username;
		
}
