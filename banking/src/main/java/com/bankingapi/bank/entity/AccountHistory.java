package com.bankingapi.bank.entity;

import java.math.BigInteger;
import java.util.List;

import com.bankingapi.bank.entity.TransactionEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountHistory {
	
	private BigInteger accountNumber;
	private String accountType;
	private String branchAddress;
	private int customerId;
	private String ifscCode;
	private BigInteger openingBalance;
	
	
	private List<TransactionEntity> transactionDetail;
	

}
