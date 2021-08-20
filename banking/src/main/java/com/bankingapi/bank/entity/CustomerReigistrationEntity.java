package com.bankingapi.bank.entity;

import java.util.Date;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

//@Entity(name = "customer_login")
public class CustomerReigistrationEntity {
	   
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "password")
	private String password;
	@Column(name = "registration_date")
	private Date registrationDate;
}
