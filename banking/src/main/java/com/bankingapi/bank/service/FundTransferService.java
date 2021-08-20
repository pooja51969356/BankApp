package com.bankingapi.bank.service;

import java.math.BigInteger;

import com.bankingapi.bank.dto.FundTransferResponse;

public interface FundTransferService {
	public FundTransferResponse transfer(BigInteger fromAccount,BigInteger toAccount,  Double amount);
}
