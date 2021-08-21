package com.bankingapi.bank.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bankingapi.bank.dto.LoginDetails;
import com.bankingapi.bank.dto.LoginResponseDto;
import com.bankingapi.bank.entity.CustomerEntity;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService{
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private CustomerRespository dao;
	
	
	public LoginResponseDto customerLogin(LoginDetails login){
		LoginResponseDto response = new LoginResponseDto();
		boolean flag = true;
		String message = "Login succesfull";
		CustomerEntity customerEntity = new CustomerEntity();
	//	String hashedPassword = DigestUtils.sha256Hex(login.getPassword());
		String hashedPassword = login.getPassword();
		try {
			customerEntity=dao.findByCustomerId(login.getCustomerId())   ;
			logger.info("customerLogin");
			if(customerEntity!=null) {
				
				if(customerEntity.isStatus()) {
					flag = false;
					message = "Dear Mr/Ms."+customerEntity.getFirstName()+" your account has been blocked for security reasons.";
				}
				 if(!customerEntity.isAuthorizationStatus()) {
					flag = false;
					message = "Dear Mr/Ms."+customerEntity.getFirstName()+" your account has not been activated yet";
				}
				if(!hashedPassword.equals(customerEntity.getPassword())) {
					flag = false;
					message = "Username or password is incorrect";
				}
			
			}else {
					flag = false;
					message = "No Record Fund";	
			}
		}
		catch (Exception e) {
			flag = false;
			message = "Username or password is incorrect";
			logger.error("invalid user or password");
		}
		if(flag) {
			logger.info("customer Account Details");
			response.setAccountNumber(customerEntity.getAccountDetail().get(0).getAccountNumber());
			response.setIfscCode(customerEntity.getAccountDetail().get(0).getIfscCode());
			response.setUsername(customerEntity.getFirstName());
			response.setOpeningBalance(customerEntity.getAccountDetail().get(0).getOpeningBalance());

		}
		response.setLoginStatus(flag);
		response.setResponseMessage(message);
		
		return response;
	}

}