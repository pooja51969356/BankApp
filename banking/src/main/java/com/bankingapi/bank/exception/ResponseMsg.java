package com.bankingapi.bank.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMsg {
String errcode=null;
String message=null;
	

}
