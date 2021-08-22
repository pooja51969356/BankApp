package com.bankingapi.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.Date;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bankingapi.bank.dto.BeneficiaryRequestDto;
import com.bankingapi.bank.dto.BeneficiaryResponseDto;
import com.bankingapi.bank.dto.FundTransferRequestDto;
import com.bankingapi.bank.dto.FundTransferResponse;
import com.bankingapi.bank.dto.LoginDetails;
import com.bankingapi.bank.dto.LoginResponseDto;
import com.bankingapi.bank.dto.RegisterAccountRequestDto;
import com.bankingapi.bank.dto.UserLoginResponseDto;
import com.bankingapi.bank.exception.InavalidAccountException;
import com.bankingapi.bank.exception.InvalidUserLoginException;
import com.bankingapi.bank.exception.ToAccountNumberNotFoundException;
import com.bankingapi.bank.repository.AccountHistoryRepositroy;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.service.BeneficiaryService;
import com.bankingapi.bank.service.FundTransferService;
import com.bankingapi.bank.service.LoginService;
import com.bankingapi.bank.service.UserRegistrationService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BankingApplicationTests {
	
	
	

	@Autowired
	UserRegistrationService userRegistrationService;	
	@Autowired
	LoginService loginService;	
	@Autowired
	BeneficiaryService beneficiaryService;	
	@Autowired
	FundTransferService fundTransferService;
	
	
	RegisterAccountRequestDto registerAccountRequestDto;
	UserLoginResponseDto expectedUserLoginResponseDto;
	
	LoginDetails loginDetails;
	LoginResponseDto expectedLoginResponseDto;
	
	BeneficiaryRequestDto beneficiaryRequestDto;
	BeneficiaryResponseDto expectedBeneficiaryResponseDto; 
	
	FundTransferRequestDto fundTransferRequestDto;
	FundTransferResponse expectedFundTransferResponse;
	
	
	
	
	
	
	@Test
	@Order(1)
	void TestCase_1_checkSuccesfull_UserRegistration() {

/*#####################################################################################*/		

		//Arrange
		registerAccountRequestDto=new RegisterAccountRequestDto("Rajni", "Yadav", "F", 35, new Date(1986,11,10), "rajni@gmail.com", "AKDPU9090O", "1254885214", "Lucknow");
		expectedUserLoginResponseDto=new UserLoginResponseDto(false, "Registration Succesful", "Rajni Yadav", "2021000001", null);
		
		
		//Act
		UserLoginResponseDto responseDto_1=userRegistrationService.createAccount(registerAccountRequestDto);
		
		expectedUserLoginResponseDto.setPassword(responseDto_1.getPassword());
		
		
		
		//Assert
		assertEquals(expectedUserLoginResponseDto, responseDto_1);
		
/*#####################################################################################*/
		
		//Arrange
		registerAccountRequestDto=new RegisterAccountRequestDto("Pooja", "Yadav", "F", 30, new Date(1991,4,20), "pooja@gmail.com", "AYDPU9076O", "2012547854", "Lucknow");
		expectedUserLoginResponseDto=new UserLoginResponseDto(false, "Registration Succesful", "Pooja Yadav", "2021000002", null);
		
		
		//Act
		UserLoginResponseDto responseDto_2=userRegistrationService.createAccount(registerAccountRequestDto);
		
		expectedUserLoginResponseDto.setPassword(responseDto_2.getPassword());
		
		
		
		//Assert
		assertEquals(expectedUserLoginResponseDto, responseDto_2);

/*#####################################################################################*/
		
		//Arrange
		registerAccountRequestDto=new RegisterAccountRequestDto("Bhanu", "Yadav", "M", 40, new Date(1981,8,15), "bhanu@gmail.com", "KOIOJ5423B", "5265478541", "Lucknow");
		expectedUserLoginResponseDto=new UserLoginResponseDto(false, "Registration Succesful", "Bhanu Yadav", "2021000003", null);
		
		

		//Act
		UserLoginResponseDto responseDto_3=userRegistrationService.createAccount(registerAccountRequestDto);
		
		expectedUserLoginResponseDto.setPassword(responseDto_3.getPassword());
		
		
		
		//Assert
		assertEquals(expectedUserLoginResponseDto, responseDto_3);
		
/*#####################################################################################*/		
		
		
		
		
	}
	
	

	@Test
	@Order(2)
	void TestCase_2_checkInvalidUserLoginException_UserLogin() {
		
		//Arrange
		loginDetails=new LoginDetails("2021000001", getCustomerPassword("2021000001"));
		
		
		//Act
		Executable executable_0= ()-> { loginService.customerLogin(loginDetails); };
		
		//Assert
		assertThrows(InvalidUserLoginException.class, executable_0 ,"Account must not be activated");
		
		
		
		//Arrange
		loginDetails=new LoginDetails("2021000002", getCustomerPassword("2021000002"));
		
		//Act
		Executable executable_1= ()-> { loginService.customerLogin(loginDetails); };
		
		//Assert
		assertThrows(InvalidUserLoginException.class, executable_1 ,"Account must be blocked for security reasons.");
				
		
	}
	
	
	


	
	/** 
	 * Before Execution This Test Case Must Activated Account
	 * Query Needed<br> 
	 * <b>Update public.customer_details set authorization_status=true where customer_id=?;</b>
	 **/
	@Test
	@Order(3)
	void TestCase_3_checkSuccesfull_UserLogin() {
		
		this.loginService=mock(LoginService.class);
		
		//Arrange
		loginDetails=new LoginDetails("2021000001", "rajnakdp88");
		expectedLoginResponseDto=new LoginResponseDto("2021000002", new BigInteger("1107190066"), "SAVING", "Gomti Nagar Lucknow", "SBIIN0001", 10000.00, false, "Login Succesfull", "Rajni Yadav");
		
		when(this.loginService.customerLogin(loginDetails)).thenReturn(expectedLoginResponseDto);
		
		
		//Act
		LoginResponseDto responseDto=loginService.customerLogin(loginDetails);
		
		//Assert
		assertEquals(expectedLoginResponseDto, responseDto);
		
		
	}
	
	@Test
	@Order(4)
	void TestCase_4_checkSuccesfull_addBeneficary() {
		
		
		beneficiaryRequestDto=new BeneficiaryRequestDto("2021000001", "Uaday", new BigInteger("20102001412"), "BARBO002145", "BOB Alambag,Lucknow", 10000.00);
		expectedBeneficiaryResponseDto=new BeneficiaryResponseDto("Uaday", "Beneficiary Added Success");
		
		assertEquals(expectedBeneficiaryResponseDto, beneficiaryService.createBeneficiaryAccount(beneficiaryRequestDto));		
		
		
	}
	
	
	@Test
	@Order(5)
	void TestCase_5_checkFor_InavalidAccountException_WhenAddBeneficary() {
		
		//Arrange
		beneficiaryRequestDto=new BeneficiaryRequestDto("2021000001", "Uaday", new BigInteger("20102001412"), "SBIIN0001", "Gomti Nagar Lucknow", 10000.00);
		
		//Act
		Executable executable=()->{beneficiaryService.createBeneficiaryAccount(beneficiaryRequestDto);};
		
		//Assert
		assertThrows(InavalidAccountException.class, executable,"Beneficiary account nunber already exist..!!!!!!");		
		
		
	}
	
	
	
	


/*
	
	@Test
	void TestCase_6_checkFor_beneficiaryAccountNumberNotFound_whenFundTransaction() {
		
		
		fundTransferRequestDto=new FundTransferRequestDto("2021000002", new BigInteger("20102001254"), "BARBO002145", 500.00);
		expectedFundTransferResponse=new FundTransferResponse(true, "Rs.500.0 successfully transferred to account 20102001254. Transaction Id :2", new BigInteger("1107358454"), new BigInteger("20102001254"));
		
		Executable executable=()->{fundTransferService.fundTransfer(fundTransferRequestDto);};
		
		assertThrows(ToAccountNumberNotFoundException.class,executable,"Can only transfer to Beneficiary accounts registed with Customer." );
		
	}
	
*/	
	
	@Test
	@Order(6)
	void TestCase_6_checkFor_beneficiaryAccountNumberNotFound_whenFundTransaction() {
		
		
		fundTransferRequestDto=new FundTransferRequestDto("2021000001", new BigInteger("20102001412"), "BARBO002145", 500.00);
		expectedFundTransferResponse=new FundTransferResponse(true, "Rs.500.0 successfully transferred to account 20102001412. Transaction Id :2", new BigInteger("1107358454"), new BigInteger("20102001254"));
		
		Executable executable=()->{fundTransferService.fundTransfer(fundTransferRequestDto);};
		
		assertThrows(RuntimeException.class,executable,"Can only transfer to Beneficiary accounts registed with Customer." );
		
	}
	
	
	@Test
	@Order(7)
	void TestCase_7_checkSuccesfull_fundTransaction() {
		
		//Arrange
		this.fundTransferService=mock(FundTransferService.class);
		
		fundTransferRequestDto=new FundTransferRequestDto("2021000001", new BigInteger("20102001412"), "SBIIN0001", 500.00);
		expectedFundTransferResponse=new FundTransferResponse(true, "Rs.500.0 successfully transferred to account 20102001412. Transaction Id :2", new BigInteger("1107190066"), new BigInteger("20102001412"));

		
		when(this.fundTransferService.fundTransfer(fundTransferRequestDto)).thenReturn(expectedFundTransferResponse);
		
		//Act
		FundTransferResponse responseDto=fundTransferService.fundTransfer(fundTransferRequestDto);
		
		assertEquals(expectedFundTransferResponse, responseDto,"Successfull Fund Transfer.");
		
	}
	
	
	@Autowired
	CustomerRespository customerRespository;
	
	@Autowired
	AccountHistoryRepositroy accountHistoryRepositroy;	
	
	private String getCustomerPassword(String customer_id) {
		
		return customerRespository.findPasswordByCustomerId(customer_id).getPassword();
		
	}
	
	private String getCustomeraAccountNumber(String customer_id) {
		
		//return accountHistoryRepositroy.findAccountNumberByCustomerId(customer_id);
		return null;
		
	}	

}
