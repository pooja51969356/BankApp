package com.bankingapi.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BeneficiaryResponseDto {
	/*
	 * private BigInteger toAccountNo ; private String ifsCode ; private String name
	 * ;
	 */
	private  String  beneficiaryName;
	private  String  status ;
}
