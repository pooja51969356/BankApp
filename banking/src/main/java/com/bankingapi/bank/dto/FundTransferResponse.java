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
public class FundTransferResponse {
	private boolean transferStatus;
	private String responseMessage;
	private BigInteger fromAccount;
	private BigInteger toAccount;
	
	
}
