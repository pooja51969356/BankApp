package com.bankingapi.bank.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bankingapi.bank.dto.LoginDetails;
import com.bankingapi.bank.dto.LoginResponseDto;
import com.bankingapi.bank.entity.CustomerEntity;
import com.bankingapi.bank.repository.UserRepository;
import com.bankingapi.bank.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserRepository dao;
	
	
	public LoginResponseDto customerLogin(LoginDetails login){
		LoginResponseDto response = new LoginResponseDto();
		boolean flag = true;
		String message = "Login succesfull";
		CustomerEntity customerEntity = null;
	//	String hashedPassword = DigestUtils.sha256Hex(login.getPassword());
		String hashedPassword = login.getPassword();
		try {
			customerEntity=dao.findByFirstName(login.getUsername());
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
		} 
		catch (Exception e) {
			flag = false;
			message = "Username or password is incorrect";
		}
		
		response.setLoginStatus(flag);
		response.setResponseMessage(message);
		try {
			response.setUsername(customerEntity.getFirstName());
		} catch (Exception e) {
			response.setUsername(login.getUsername());
		}
		return response;
	}

}