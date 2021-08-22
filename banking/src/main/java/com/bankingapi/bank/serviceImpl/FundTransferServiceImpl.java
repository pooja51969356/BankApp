package com.bankingapi.bank.serviceImpl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import javax.lang.model.type.UnknownTypeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankingapi.bank.dto.FundTransferRequestDto;
import com.bankingapi.bank.dto.FundTransferResponse;
import com.bankingapi.bank.entity.AccountEntity;
import com.bankingapi.bank.entity.BeneficiaryEntity;
import com.bankingapi.bank.entity.CustomerEntity;
import com.bankingapi.bank.entity.TransactionEntity;
import com.bankingapi.bank.exception.InavalidAccountException;
import com.bankingapi.bank.exception.InsufficientBalanceException;
import com.bankingapi.bank.exception.ToAccountNumberNotFoundException;
import com.bankingapi.bank.repository.AccountHistoryRepositroy;
import com.bankingapi.bank.repository.BeneficiaryRepository;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.repository.FundTransferRepository;
import com.bankingapi.bank.service.FundTransferService;

import io.swagger.models.auth.In;


@Service
public class FundTransferServiceImpl implements FundTransferService{
	private static final Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);

	@Autowired
	private FundTransferRepository fdao;
	@Autowired
	private AccountHistoryRepositroy ahdao;
	@Autowired
	private BeneficiaryRepository bdao;
	@Autowired
	private CustomerRespository cdao;
	
	
	@Override
	public FundTransferResponse fundTransfer(FundTransferRequestDto fundTransferRequestDto) {

		try {

			Optional<BeneficiaryEntity> beneficiaryEntityDetail =getBeneficiaryDetailByAccountNumber(fundTransferRequestDto.getBeneficiaryAccountNo());

			

			logger.info("beneficiary Account No in DB"+fundTransferRequestDto.getBeneficiaryAccountNo());
			//check beneficiary account no exist
			if(beneficiaryEntityDetail.isEmpty() || beneficiaryEntityDetail.get().getAccountNo().compareTo(fundTransferRequestDto.getBeneficiaryAccountNo())!=0 ) {
				String msg="AccountNo "+fundTransferRequestDto.getBeneficiaryAccountNo()+" not found in Beneficiary. You can only transfer to Beneficiary accounts registed with you.";
				logger.info(msg);
				throw new ToAccountNumberNotFoundException(msg);
			}
			
			CustomerEntity customerEntityDetail=getByCustomerId(fundTransferRequestDto.getCustomerId());
			//check minimum account for transfer
			if(customerEntityDetail.getAccountDetail().get(0).getOpeningBalance() <fundTransferRequestDto.getAmount()) {
				
				String msg="Insufficient funds to complete the transaction";
				logger.info(msg);
				throw new InsufficientBalanceException(msg);
			}
			
			
			BigInteger fromAccount=customerEntityDetail.getAccountDetail().get(0).getAccountNumber();
			
			return transfer( fromAccount, fundTransferRequestDto.getBeneficiaryAccountNo(),   fundTransferRequestDto.getAmount());
			
			
			

		} catch (Exception e) {
			
			logger.info("Transaction Fail");
			throw new RuntimeException("Transaction Fail..!!! "+e.getLocalizedMessage());
			
		}


	}
	
	
	private FundTransferResponse transfer(BigInteger fromAccount,BigInteger toAccount,  Double amount) {
		
		FundTransferResponse response=new FundTransferResponse();
		boolean flag=true;
		
		try {
			
			logger.info("enter in block");
			
			
			AccountEntity senderAccount=ahdao.findByAccountNumber(fromAccount);
			senderAccount.setOpeningBalance(senderAccount.getOpeningBalance()-amount);
			
			
			Optional<BeneficiaryEntity> receiverAccount=bdao.findByAccountNo(toAccount);
			
			if(receiverAccount.get().getBalance()!=null)
				receiverAccount.get().setBalance( (receiverAccount.get().getBalance()+amount));
			else
				receiverAccount.get().setBalance( (amount));
			
			
			
			TransactionEntity transactionEntity =fdao.save(prepareTransaction(fromAccount, toAccount, amount));	//transfer fund
			AccountEntity accountEntity =ahdao.save(senderAccount);					//update customer opening balance	
			BeneficiaryEntity beneficiaryEntity =bdao.save(receiverAccount.get());  	//update beneficiary balance
			
			logger.info("save data");
			
			
			if(transactionEntity !=null && accountEntity!=null && beneficiaryEntity!=null) {
				
				response.setResponseMessage("Rs."+amount+" successfully transferred to account "+toAccount+". Transaction Id :"+transactionEntity.getTransactionId());
				response.setTransferStatus(flag);
				
			}else {
				
				response.setResponseMessage("Rs."+amount+"  not transferred to account "+toAccount);
				response.setTransferStatus(flag);
				
			}
				



		}catch (Exception e) {
			
			logger.error("Invalid Transaction");
			throw new InavalidAccountException("Transaction Fail....!!!");
		}
		
		
		response.setFromAccount(fromAccount);
		response.setToAccount(toAccount);
		
		
		return response;
	}

	
	
	
	public TransactionEntity prepareTransaction(BigInteger fromAccount, BigInteger toAccount, Double amount) {
		
		java.util.Date date=new java.util.Date();
		
		TransactionEntity transfer=new TransactionEntity();
		transfer.setToAccount(toAccount);
		transfer.setFromAccount(fromAccount);
		transfer.setAmount(amount);
		transfer.setTransactionTime(date);
		
		return transfer;
	}

	

	@Override
	public Optional<BeneficiaryEntity> getBeneficiaryDetailByAccountNumber(BigInteger accountNumber) {
		// TODO Auto-generated method stub
		return bdao.findByAccountNo(accountNumber);
	}



	@Override
	public CustomerEntity getByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return cdao.findByCustomerId(customerId);
	}

	
	
}
