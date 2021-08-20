package com.bankingapi.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapi.bank.dto.LoginDetails;
import com.bankingapi.bank.dto.LoginResponseDto;
import com.bankingapi.bank.serviceImpl.LoginServiceImpl;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
	
	@Autowired
	LoginServiceImpl service;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginDetails details) {
		
		return ResponseEntity.ok( service.customerLogin(details));
		
	}
}
