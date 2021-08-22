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
public class FundTransferRequestDto {
	
	private  String customerId;
	private  BigInteger beneficiaryAccountNo;
	private  String beneficiaryIfsCode;
	private  Double  amount;

}
