package com.bankingapi.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapi.bank.dto.BenificiaryRequestDto;
import com.bankingapi.bank.dto.BenificiaryResponseDto;
import com.bankingapi.bank.serviceImpl.BenificiaryServiceImpl;

@RestController
@Validated
public class BenificiaryController {
@Autowired
BenificiaryServiceImpl service;


@RequestMapping(path = "api/benificiary/createAccount",method = RequestMethod.POST)
public ResponseEntity<BenificiaryResponseDto>createBenificiaryAccount(@RequestBody BenificiaryRequestDto benificiaryRequestDto) {
	
	return ResponseEntity.ok(service.createBenificiaryAccount(benificiaryRequestDto));

}
}
