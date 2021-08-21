package com.bankingapi.bank.serviceImpl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapi.bank.dto.BenificiaryRequestDto;
import com.bankingapi.bank.dto.BenificiaryResponseDto;
import com.bankingapi.bank.entity.BenificiaryEntity;
import com.bankingapi.bank.exception.InavalidAccountException;
import com.bankingapi.bank.repository.BenificiaryRepository;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.service.BenificiaryService;
@Service
public class BenificiaryServiceImpl implements BenificiaryService{
	
	private static final Logger logger = LoggerFactory.getLogger(BenificiaryServiceImpl.class);

	@Autowired
	BenificiaryRepository dao;
	@Autowired
	CustomerRespository cdao;

	@SuppressWarnings("null")
	public BenificiaryResponseDto createBenificiaryAccount(BenificiaryRequestDto benificiaryDao) {
		BenificiaryResponseDto benificiaryResponseDto = new BenificiaryResponseDto();
		BenificiaryEntity benificiary = new BenificiaryEntity();
		benificiaryResponseDto.setStatus("Success");
		//logger.info("enter block");
		if(cdao.findByCustomerId(benificiaryDao.getCustomerId())==null) {
			logger.info("Invalid CustomerId");
			throw new InavalidAccountException("CustomerId account not exist!!!!!!");
		}
		if(dao.findByAccountNo(benificiaryDao.getToAccountNo()).getAccountNo()!=null) {
			logger.info("Invalid Benificiary input");
			throw new InavalidAccountException("Benificiary account no already exist!!!!!!");
		}
		
		 benificiary=dao.save(prepareBenificiaryDao(benificiary, benificiaryDao));
		return prepareBenificiaryResponseDto(benificiary, benificiaryResponseDto);
	}
	private BenificiaryEntity prepareBenificiaryDao(BenificiaryEntity responseMas,BenificiaryRequestDto benificiaryRequestDto) {
		responseMas.setAccountNo(benificiaryRequestDto.getToAccountNo());
		responseMas.setIfsCode(benificiaryRequestDto.getIfsCode());
		responseMas.setName(benificiaryRequestDto.getName());	
		
		return responseMas;
	}
	private BenificiaryResponseDto prepareBenificiaryResponseDto(BenificiaryEntity responseMas,BenificiaryResponseDto benificiaryResponseDto) {
		benificiaryResponseDto.setToAccountNo(responseMas.getAccountNo());
		benificiaryResponseDto.setIfsCode(responseMas.getIfsCode());
		benificiaryResponseDto.setName(responseMas.getName());		
		return benificiaryResponseDto;
	}
}
