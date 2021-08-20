package com.bankingapi.bank.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterAccountRequestDto {
	
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private Date dob;
	private String email;
	private String panNumber;
	private String adhaarNumber;
	private String address;
}
