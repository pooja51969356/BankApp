package com.bankingapi.bank.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bankingapi.bank.entity.BeneficiaryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BeneficiaryRequestDto {
	
	private  String customerId;
	private  String  beneficiaryName;
	private  BigInteger beneficiaryAccountNo;
	private  String beneficiaryIfsCode;
	private  String  beneficiaryBranchAdd;			
	private  Double  maxTransactionAmountLimit;
	

}
