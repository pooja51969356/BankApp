package com.bankingapi.bank.entity;

import javax.persistence.Entity;

import lombok.Data;



import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "account_details")
public class AccountEntity {
	
	@Id
//	@Digits(integer = 8, fraction = 1, message = "InValid accountNumber")
	@Column(name = "account_number")
	private long  accountNumber;
	@Column(name = "account_type")
	private String accountType;
	@Column(name = "branch_address")
	private String branchAddress;
//	@Column(name = "customer_id")
//	private int customerId;
	//@NotEmpty(message = "ifsccode should not be empty")
	@Column(name = "ifsc_code")
	private String ifscCode;
	@Column(name = "opening_balance")
	private BigInteger openingBalance;
	

	

}


