package com.bankingapi.bank.dto;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterAccountResponseDto {
	
    private int customerId;
    private String name;
	private Long  accountNumber;
	private String accountType;
	private String branchAddress;
	private String ifscCode;
	private BigInteger openingBalance;

}