package com.bankingapi.bank.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bankingapi.bank.entity.AccountEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDetailsDto {
	
    private String address;
	
    private int age;
	
	@Temporal(TemporalType.DATE)
    private Date dob;

    private String emailId;
	
    private String firstName;

    private String gender;
	
    private String lastName;
    private String adhaarNumber;
    private String panNumber;
    private String phoneNumber;
	
}
