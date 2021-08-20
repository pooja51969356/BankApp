package com.bankingapi.bank.serviceImpl;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapi.bank.dto.RegisterAccountRequestDto;
import com.bankingapi.bank.dto.RegisterAccountResponseDto;
import com.bankingapi.bank.entity.AccountEntity;
import com.bankingapi.bank.entity.CustomerEntity;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.service.UserRegistrationService;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

	@Autowired
	CustomerRespository customerRespository;
	
	
	

	@Override
	public List<RegisterAccountResponseDto> createAccount(RegisterAccountRequestDto registerAccountRequestDto) {
		
		//check basic validation before save customer detail
		if(registerAccountRequestDto==null) {
			//thow exception
		}
		
		CustomerEntity panBasedDetails=customerRespository.findByPanNumber(registerAccountRequestDto.getPanNumber());
		if(panBasedDetails!=null) {
			//thow exception details already exist
		}
		CustomerEntity responseMas=customerRespository.save(prepareCustomerDetail(registerAccountRequestDto));
		
		System.out.println(" CustomerId: "+responseMas.getCustomerId()+", AccountId: "+responseMas.getAccountDetail().get(0).getAccountNumber());
		
		
		return prepareRegisterAccountResponseDto(responseMas);
	}


	private CustomerEntity prepareCustomerDetail(RegisterAccountRequestDto registerAccountRequestDto) {
		
		CustomerEntity customerMas=new CustomerEntity();
		
		customerMas.setFirstName(registerAccountRequestDto.getFirstName());
		customerMas.setLastName(registerAccountRequestDto.getLastName());
		customerMas.setGender(registerAccountRequestDto.getGender());
		customerMas.setAge(registerAccountRequestDto.getAge());
		customerMas.setAdhaarNumber(registerAccountRequestDto.getAdhaarNumber());
		customerMas.setPanNumber(registerAccountRequestDto.getPanNumber());
		customerMas.setAddress(registerAccountRequestDto.getAddress());		
		customerMas.setAccountDetail(prepareAccountDetail());
		
		
		return customerMas;
	}
	public static int accnoGeneration() {
		int n=9;
	    int m = (int) Math.pow(10, n - 1);
	    return m + new Random().nextInt(9 * m);
	}

	private List<AccountEntity> prepareAccountDetail() {
		
		AccountEntity accountMas=new AccountEntity();
		accountMas.setAccountNumber(accnoGeneration());
		accountMas.setAccountType("Saving");
		accountMas.setBranchAddress("Gomti Nagar Lucknow");
		accountMas.setIfscCode("SBIIN0001");
		accountMas.setOpeningBalance(new BigInteger("500"));
		
		List<AccountEntity> list=new ArrayList<>();
		list.add(accountMas);
		
		return list;
		
	}


	private List<RegisterAccountResponseDto> prepareRegisterAccountResponseDto(CustomerEntity responseMas) {
		
		RegisterAccountResponseDto accountResponseDto=new RegisterAccountResponseDto();
		
		accountResponseDto.setCustomerId(responseMas.getCustomerId());
		accountResponseDto.setName(responseMas.getFirstName()+" "+responseMas.getLastName());
		accountResponseDto.setAccountNumber(responseMas.getAccountDetail().get(0).getAccountNumber());
		accountResponseDto.setAccountType(responseMas.getAccountDetail().get(0).getAccountType());
		accountResponseDto.setBranchAddress(responseMas.getAccountDetail().get(0).getBranchAddress());
		accountResponseDto.setIfscCode(responseMas.getAccountDetail().get(0).getIfscCode());
		accountResponseDto.setOpeningBalance(responseMas.getAccountDetail().get(0).getOpeningBalance());
		
		
		return List.of(accountResponseDto);
	}
	
	

}
