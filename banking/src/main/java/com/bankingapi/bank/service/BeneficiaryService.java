package com.bankingapi.bank.service;

import com.bankingapi.bank.dto.BeneficiaryRequestDto;
import com.bankingapi.bank.dto.BeneficiaryResponseDto;

public interface BeneficiaryService {

	public BeneficiaryResponseDto createBeneficiaryAccount(BeneficiaryRequestDto benificiaryDto);
}
