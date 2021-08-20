package com.bankingapi.bank.controller;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapi.bank.dto.BenificiaryRequestDto;
import com.bankingapi.bank.dto.FundTransferResponse;
import com.bankingapi.bank.entity.BenificiaryEntity;
import com.bankingapi.bank.entity.CustomerEntity;
import com.bankingapi.bank.entity.CustomerReigistrationEntity;
import com.bankingapi.bank.repository.BenificiaryRepository;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.serviceImpl.FundTransferServiceImpl;


@RestController
public class FundTransferController {
	
	
	
		
	
	
	@Autowired
	FundTransferServiceImpl service;
	private final String ifsc="ICIN7465";
	
	

	@PostMapping("/account/transfer")
	public FundTransferResponse transfer(@RequestBody BenificiaryRequestDto details) {
		try {
			if(details.getIfsCode().equals(ifsc)) 
			{
					BenificiaryEntity p=service.getByName(details.getName());
					CustomerEntity s=service.getByCustomerId(details.getCustomerId());
					BigInteger fromAccount=s.getAccountDetail().get(0).getAccountNumber();
						if(p.getAccountNo()==details.getToAccountNo()) {
					
							return service.transfer(fromAccount,details.getToAccountNo(),details.getAmount());
						
						}
						else {
							FundTransferResponse response=new FundTransferResponse();
							response.setToAccount(details.getToAccountNo());
							response.setResponseMessage("Dear user You can only transfer funds from the accounts registed with you");
							response.setTransferStatus(false);
							return response;
			}
			}
			else {
				FundTransferResponse response=new FundTransferResponse();
						response.setToAccount(details.getToAccountNo());
						response.setResponseMessage("IFSC code is incorrect");
						response.setTransferStatus(false);
						return response;
			}
		} catch (Exception e) {
			FundTransferResponse response=new FundTransferResponse();
			response.setToAccount(details.getToAccountNo());
			response.setResponseMessage("Please provide an IFSC code");
			response.setTransferStatus(false);
			return response;
			
		}
	}
	

}
