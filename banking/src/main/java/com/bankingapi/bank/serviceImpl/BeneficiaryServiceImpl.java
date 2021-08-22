package com.bankingapi.bank.serviceImpl;



import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapi.bank.dto.BeneficiaryRequestDto;
import com.bankingapi.bank.dto.BeneficiaryResponseDto;
import com.bankingapi.bank.entity.BeneficiaryEntity;
import com.bankingapi.bank.exception.InavalidAccountException;
import com.bankingapi.bank.repository.BeneficiaryRepository;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.service.BeneficiaryService;
@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{
	
	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);

	@Autowired
	BeneficiaryRepository dao;
	@Autowired
	CustomerRespository cdao;

	@Override
	public BeneficiaryResponseDto createBeneficiaryAccount(BeneficiaryRequestDto beneficiaryDto){
		
		//logger.info("enter block");
		
		//check customer id exist
		if(cdao.findByCustomerId(beneficiaryDto.getCustomerId())==null) {							 
			logger.info("Invalid CustomerId");
			throw new InavalidAccountException("CustomerId account not exist!!!!!!");
		}
		
		
		Optional<BeneficiaryEntity> beneficiaryEntity =dao.findByAccountNo(beneficiaryDto.getBeneficiaryAccountNo());
		
		if(!beneficiaryEntity.isEmpty()) {			
			//check beneficiary a/c no exist or not													
			logger.info("Invalid Beneficiary Detail");
			throw new InavalidAccountException("Beneficiary account nunber already exist!!!!!!");
		}
		
		BeneficiaryEntity beneficiaryResponse=new BeneficiaryEntity();
		
		try {
		
			beneficiaryResponse=dao.save(prepareBeneficiaryDao(beneficiaryDto));
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Beneficiary Not Added Succesfully..!! Resson: "+e.toString());
		}
		
		
		
		return prepareBeneficiaryResponseDto(beneficiaryResponse,beneficiaryDto);
		
		
		
	}
	private BeneficiaryEntity prepareBeneficiaryDao(BeneficiaryRequestDto beneficiaryRequestDto) {
		
		BeneficiaryEntity responseMas = new BeneficiaryEntity();
		
		responseMas.setAccountNo(beneficiaryRequestDto.getBeneficiaryAccountNo());
		responseMas.setIfsCode(beneficiaryRequestDto.getBeneficiaryIfsCode());
		responseMas.setName(beneficiaryRequestDto.getBeneficiaryName());
		responseMas.setBranchAdd(beneficiaryRequestDto.getBeneficiaryBranchAdd());
		responseMas.setBalance(beneficiaryRequestDto.getMaxTransactionAmountLimit());
		return responseMas;
	}
	private BeneficiaryResponseDto prepareBeneficiaryResponseDto(BeneficiaryEntity responseMas,BeneficiaryRequestDto beneficiaryDto) {
		
		//validation
		
		BeneficiaryResponseDto beneficiaryResponseDto = new BeneficiaryResponseDto();
		beneficiaryResponseDto.setBeneficiaryName(responseMas.getName());
		beneficiaryResponseDto.setStatus("Beneficiary Added Success");
		
		return beneficiaryResponseDto;
	}
}
