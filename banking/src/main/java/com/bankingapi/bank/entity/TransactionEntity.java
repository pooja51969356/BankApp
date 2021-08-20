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
@Entity(name="transaction_details")
public class TransactionEntity {

	@Id
	@GeneratedValue
	@Column(name="transaction_id")
	private int transactionId ;
	@Column(name = "amount")
	private  BigInteger amount ;
	@Column(name = "from_account")
	private  BigInteger  fromAccount ;
	@Column(name = "to_account")
	private  BigInteger  toAccount ;
	@Column(name = "transaction_time")
	private Date transactionTime ;
	@Column(name = "type")
	private  String type ;
}
