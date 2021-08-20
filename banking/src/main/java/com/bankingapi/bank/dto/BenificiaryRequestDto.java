package com.bankingapi.bank.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bankingapi.bank.entity.BenificiaryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BenificiaryRequestDto {
	
	private BigInteger toAccountNo ;
	private String customerId ;
	private  String ifsCode ;	
	private  Double  amount ;
	private  String  name ;

}
