package com.bankingapi.bank.serviceImpl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapi.bank.dto.FundTransferResponse;
import com.bankingapi.bank.entity.AccountEntity;
import com.bankingapi.bank.entity.BenificiaryEntity;
import com.bankingapi.bank.entity.CustomerEntity;
import com.bankingapi.bank.entity.TransactionEntity;
import com.bankingapi.bank.repository.AccountHistoryRepositroy;
import com.bankingapi.bank.repository.BenificiaryRepository;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.repository.FundTransferRepository;
import com.bankingapi.bank.service.FundTransferService;


@Service
public class FundTransferServiceImpl implements FundTransferService{
	private static final Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);

	@Autowired
	private FundTransferRepository Fdao;
	@Autowired
	private AccountHistoryRepositroy dao;
	@Autowired
	private BenificiaryRepository bdao;
	@Autowired
	private CustomerRespository cdao;
	
	public FundTransferResponse transfer(BigInteger fromAccount,BigInteger toAccount,  Double amount) {
		FundTransferResponse response=new FundTransferResponse();
		boolean flag=true;
		
		try {
			AccountEntity senderAccount=dao.findByAccountNumber(fromAccount);
		BenificiaryEntity receiverAccount=bdao.findByAccountNo(toAccount);
				if(senderAccount.getAccountNumber()!=null) 
				{
					logger.info("enter in block");
					if(senderAccount.getOpeningBalance()>amount) {
						logger.info("amount Check");
						senderAccount.setOpeningBalance(senderAccount.getOpeningBalance()-amount);
						receiverAccount.setBalance(receiverAccount.getBalance()+amount);
						addAction(fromAccount, toAccount, amount);
						dao.save(senderAccount);
						bdao.save(receiverAccount);  ///if want to update beneficiary amount
						logger.info("save data");
						response.setResponseMessage("Rs."+amount+" successfully transferred to account "+toAccount);
						response.setTransferStatus(flag);
					
					}
					else {
						logger.info("amount Insufficient");
						flag=false;
						response.setResponseMessage("Insufficient funds to complete the transfer");
						response.setTransferStatus(flag);
						}
				}
				else {
					logger.info("Account number is not exist");
					flag=false;
					response.setResponseMessage("Account number is not exist");
					response.setTransferStatus(flag);
				}
			
			
		} 
		
		catch (Exception e) {
			logger.error("Invalid Account number");
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setTransferStatus(flag);
		}
		response.setFromAccount(fromAccount);
		response.setToAccount(toAccount);
		return response;
	}

	
	
	
	public TransactionEntity addAction(BigInteger fromAccount, BigInteger toAccount, Double amount) {
		java.util.Date date=new java.util.Date();
		TransactionEntity transfer=new TransactionEntity();
		transfer.setToAccount(toAccount);
		transfer.setFromAccount(fromAccount);
		transfer.setAmount(amount);
		transfer.setTransactionTime(date);
		return Fdao.save(transfer);
	}




	public BenificiaryEntity getByName(String name) {
		// TODO Auto-generated method stub
		return bdao.findByName(name);
	}




	public CustomerEntity getByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return cdao.findByCustomerId(customerId);
	}

	
	
}
