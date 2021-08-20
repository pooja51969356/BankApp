package com.bankingapi.bank.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Entity(name = "customer_login_details")
public class CustomerReigistrationEntity {
	   
	@Column(name = "customer_id")
	private int customerId;
	@Column(name = "password")
	private String password;
	@Column(name = "registration_date")
	private Date registrationDate;
}
