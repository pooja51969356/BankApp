package com.bankingapi.bank.serviceImpl;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bankingapi.bank.dto.RegisterAccountRequestDto;
import com.bankingapi.bank.dto.RegisterAccountResponseDto;
import com.bankingapi.bank.dto.UserLoginResponseDto;
import com.bankingapi.bank.entity.AccountEntity;
import com.bankingapi.bank.entity.CustomerEntity;
import com.bankingapi.bank.entity.CustomerReigistrationEntity;
import com.bankingapi.bank.exception.InavalidAccountException;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.service.UserRegistrationService;
import com.bankingapi.bank.utility.PasswordUtil;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	CustomerRespository customerRespository;
	
	
	

	@Override
	public UserLoginResponseDto createAccount(RegisterAccountRequestDto registerAccountRequestDto) {
		
		//check basic validation before save customer detail
		if(registerAccountRequestDto==null) {
			 throw new InavalidAccountException("Please check Details");
		}
				
		boolean flag = true;
		String message = "Registration Succesful";
		CustomerEntity responseMas = new CustomerEntity();

		UserLoginResponseDto accountResponseDto=new UserLoginResponseDto();	
		if(EmailAlreadyExists(registerAccountRequestDto.getEmail())) {
			message = "Email already Exists";
			flag = false;
		}
		
		else if(AdhaarNumberAlreadyExists(registerAccountRequestDto.getAdhaarNumber())) {
			message = "Phone number already Exists";
			flag = false;
		}
		
		else if(PanAlreadyExists(registerAccountRequestDto.getPanNumber())) {
			message = "Username already Exists";
			flag = false;
		}
		
		
		if(!flag) {
			
			throw new InavalidAccountException(message);
		}
		
		accountResponseDto.setRegistrationStatus(flag);
		accountResponseDto.setResponseMessage(message);	
		responseMas=customerRespository.save(prepareCustomerDetail(registerAccountRequestDto));
		
		return prepareUserLoginResponseDto(responseMas,accountResponseDto);	
	
		
	
				
		
				
			
		
	}


	private CustomerEntity prepareCustomerDetail(RegisterAccountRequestDto registerAccountRequestDto) {
		
		CustomerEntity customerMas=new CustomerEntity();
		customerMas.setCustomerId(CustomerIdGeneration());	
		customerMas.setPassword(CustomerPasswordGeneration());
		customerMas.setFirstName(registerAccountRequestDto.getFirstName());
		customerMas.setLastName(registerAccountRequestDto.getLastName());
		customerMas.setGender(registerAccountRequestDto.getGender());
		customerMas.setAge(registerAccountRequestDto.getAge());
		customerMas.setEmailId(registerAccountRequestDto.getEmail());
		customerMas.setDob(registerAccountRequestDto.getDob());
		customerMas.setAdhaarNumber(registerAccountRequestDto.getAdhaarNumber());
		customerMas.setPanNumber(registerAccountRequestDto.getPanNumber());
		customerMas.setAddress(registerAccountRequestDto.getAddress());	
		customerMas.setStatus(true);
		customerMas.setAccountDetail(prepareAccountDetail());
		
		
		return customerMas;
	}
	public static BigInteger accnoGeneration() {
		BigInteger no =BigInteger.valueOf(12344444);
		return no;
	}
	public static String  CustomerIdGeneration() {
		
		String	genCustomerId ="12233" ;
		return genCustomerId;
	}
	
	////Password generate
	public static String CustomerPasswordGeneration() {
		String password = PasswordUtil.generatePassword();
		return password;
	}
	private List<AccountEntity> prepareAccountDetail() {
		
		AccountEntity accountMas=new AccountEntity();
		accountMas.setAccountNumber(accnoGeneration());
		accountMas.setAccountType("Saving");
		accountMas.setBranchAddress("Gomti Nagar Lucknow");
		accountMas.setIfscCode("SBIIN0001");
		accountMas.setOpeningBalance(10000.0);
		
		List<AccountEntity> list=new ArrayList<>();
		list.add(accountMas);
		
		return list;
		
	}
///Success Password andCustomerId Response
	private UserLoginResponseDto prepareUserLoginResponseDto(CustomerEntity responseMas,UserLoginResponseDto accountResponseDto) {
		accountResponseDto.setRegistrationStatus(responseMas.isStatus());			
		accountResponseDto.setCustomerId(responseMas.getCustomerId());
		accountResponseDto.setPassword(responseMas.getPassword());
		accountResponseDto.setName(responseMas.getFirstName()+" "+responseMas.getLastName());		
		
		return accountResponseDto;
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

	
	/*
	 * public boolean usernameAlreadyExists(String username) { try { CustomerEntity
	 * u=customerRespository.findByUsername(username);
	 * System.out.println(u.toString()); return true; } catch (Exception e) { }
	 * return false; }
	 */
	public boolean PanAlreadyExists(String panno) {
		try {
			CustomerEntity u=customerRespository.findByPanNumber(panno);
			System.out.println(u.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public boolean EmailAlreadyExists(String email) {
		try {
			CustomerEntity u=customerRespository.findByEmailId(email);
			System.out.println(u.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	

	public boolean AdhaarNumberAlreadyExists(String l) {
		try {
			CustomerEntity u=customerRespository.findByAdhaarNumber(l);
			System.out.println(u.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	

}
