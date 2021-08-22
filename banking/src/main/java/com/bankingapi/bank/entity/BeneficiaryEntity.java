package com.bankingapi.bank.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="beneficiary_details")
public class BeneficiaryEntity {
	
    
    
    
	@Id
	@Column(name="account_no")
	private BigInteger accountNo ;
	@Column(name = "ifs_code")
	private  String ifsCode ;
	@Column(name = "branch_add")
	private  String  branchAdd ;
	@Column(name = "name")
	private  String  name ;
	@Column(name = "balance")
	private  Double  balance ;

}
