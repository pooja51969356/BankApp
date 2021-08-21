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
public class BenificiaryResponseDto {
	private BigInteger toAccountNo ;	
	private  String ifsCode ;	
	private  String  name ;
	private  String  status ;
}
