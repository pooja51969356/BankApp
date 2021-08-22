package com.bankingapi.bank.service;

import java.math.BigInteger;
import java.util.Optional;

import com.bankingapi.bank.dto.FundTransferRequestDto;
import com.bankingapi.bank.dto.FundTransferResponse;
import com.bankingapi.bank.entity.BeneficiaryEntity;
import com.bankingapi.bank.entity.CustomerEntity;

public interface FundTransferService {
	
	
	public FundTransferResponse fundTransfer(FundTransferRequestDto fundTransferRequestDto);
	public Optional<BeneficiaryEntity> getBeneficiaryDetailByAccountNumber(BigInteger accountNumber);
	public CustomerEntity getByCustomerId(String customerId);
}
