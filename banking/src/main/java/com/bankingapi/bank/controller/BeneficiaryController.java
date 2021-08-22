package com.bankingapi.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapi.bank.dto.BeneficiaryRequestDto;
import com.bankingapi.bank.dto.BeneficiaryResponseDto;
import com.bankingapi.bank.serviceImpl.BeneficiaryServiceImpl;

@RestController
@Validated
public class BeneficiaryController {
@Autowired
BeneficiaryServiceImpl service;


@RequestMapping(path = "api/user/beneficiary/addBeneficiary",method = RequestMethod.POST)
public ResponseEntity<BeneficiaryResponseDto>createBeneficiaryAccount(@RequestBody BeneficiaryRequestDto benificiaryRequestDto) {
	
	return ResponseEntity.ok(service.createBeneficiaryAccount(benificiaryRequestDto));

}
}
