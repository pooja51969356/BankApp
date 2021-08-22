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
public class LoginResponseDto {
	   private String customerId;
		private BigInteger  accountNumber;
		private String accountType;
		private String branchAddress;
		private String ifscCode;
		private Double openingBalance;
		private boolean loginStatus;
		private String responseMessage;
		private String username;
		
}
