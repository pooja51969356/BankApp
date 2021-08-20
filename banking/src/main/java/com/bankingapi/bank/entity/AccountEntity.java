package com.bankingapi.bank.entity;

import javax.persistence.Entity;


import lombok.Data;



import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@SequenceGenerator(name="account_details_account_number_seq",sequenceName="account_details_account_number_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="account_details_account_number_seq")
	@Column(name = "account_number")
	private long  accountNumber;
	@Column(name = "account_type")
	private String accountType;
	@Column(name = "branch_address")
	private String branchAddress;
//	@Column(name = "customer_id")
//	private int customerId;
	@Column(name = "ifsc_code")
	private String ifscCode;
	@Column(name = "opening_balance")
	private BigInteger openingBalance;
	

	

}


