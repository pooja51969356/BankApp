package com.bankingapi.bank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapi.bank.dto.FundTransferRequestDto;
import com.bankingapi.bank.dto.FundTransferResponse;
import com.bankingapi.bank.service.FundTransferService;


@RestController
@Validated
public class FundTransferController {
	
	
	
		
	
	
	@Autowired
	FundTransferService fundTransferService;
	private final String ifsc="ICIN7465";
	

	
	@PostMapping("api/user/account/fund-transfer")
	public ResponseEntity<FundTransferResponse> fundTransfer(@RequestBody FundTransferRequestDto fundTransferRequestDto) {
			
			return ResponseEntity.ok(fundTransferService.fundTransfer(fundTransferRequestDto));

	}	

/*	
	@PostMapping("api/user/account/fund-transfer")
	public ResponseEntity<FundTransferResponse> fundTransfer_1(@RequestBody FundTransferRequestDto fundTransferRequestDto) {
		try {
			if(fundTransferRequestDto.getBeneficiaryIfsCode().equals(ifsc)) 
			{
					BeneficiaryEntity p=fundTransferService.getByName(fundTransferRequestDto.getBeneficiaryName());
					
					CustomerEntity s=fundTransferService.getByCustomerId(fundTransferRequestDto.getCustomerId());
					
					BigInteger fromAccount=s.getAccountDetail().get(0).getAccountNumber();
					
						if(p.getAccountNo()==fundTransferRequestDto.getBeneficiaryAccountNo()) {
					
							return ResponseEntity.ok(  fundTransferService.transfer(fromAccount,fundTransferRequestDto.getBeneficiaryAccountNo(),fundTransferRequestDto.getMaxTransactionAmountLimit()));
						
						}
						else {
							FundTransferResponse response=new FundTransferResponse();
							response.setToAccount(fundTransferRequestDto.getBeneficiaryAccountNo());
							response.setResponseMessage("Dear user You can only transfer funds from the accounts registed with you");
							response.setTransferStatus(false);
							return ResponseEntity.ok(response);
						}
			}
			else {
				FundTransferResponse response=new FundTransferResponse();
						response.setToAccount(fundTransferRequestDto.getBeneficiaryAccountNo());
						response.setResponseMessage("IFSC code is incorrect");
						response.setTransferStatus(false);
						return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			FundTransferResponse response=new FundTransferResponse();
			response.setToAccount(fundTransferRequestDto.getBeneficiaryAccountNo());
			response.setResponseMessage("Please provide an IFSC code");
			response.setTransferStatus(false);
			return ResponseEntity.ok(response);
			
		}
	}
*/	

}
