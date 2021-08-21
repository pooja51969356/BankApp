package com.bankingapi.bank.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



//import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice  extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<ResponseMsg> handleNotUserFoundException(CustomerNotFoundException ex) {
		ResponseMsg responsemsg = new ResponseMsg();
		responsemsg.setErrcode("121");
		responsemsg.setMessage(ex.getMessage());

		return new ResponseEntity<ResponseMsg>(responsemsg, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NullPointerException.class)	   
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("Issue in doing the action:"+exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = InsufficientBalanceException.class)
	public ResponseEntity<ResponseMsg> handleBalanceFoundException(InsufficientBalanceException ex) {
		ResponseMsg responsemsg = new ResponseMsg();
		responsemsg.setErrcode("101");
		responsemsg.setMessage(ex.getMessage());

		return new ResponseEntity<ResponseMsg>(responsemsg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InavalidAccountException.class)
	public ResponseEntity<ResponseMsg> handleAccountNotFoundException(InavalidAccountException ex) {
		ResponseMsg responsemsg = new ResponseMsg();
		responsemsg.setErrcode("102");
		responsemsg.setMessage(ex.getMessage());

		return new ResponseEntity<ResponseMsg>(responsemsg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ToAccountNumberNotFoundException.class)
	public ResponseEntity<ResponseMsg> handleToAccountNotFoundException(ToAccountNumberNotFoundException ex) {
		ResponseMsg responsemsg = new ResponseMsg();
		responsemsg.setErrcode("1023");
		responsemsg.setMessage(ex.getMessage());

		return new ResponseEntity<ResponseMsg>(responsemsg, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = FromAccountNumberNotFoundException.class)
	public ResponseEntity<ResponseMsg> handleAccounFromNotFoundException(FromAccountNumberNotFoundException ex) {
		ResponseMsg responsemsg = new ResponseMsg();
		responsemsg.setErrcode("1024");
		responsemsg.setMessage(ex.getMessage());

		return new ResponseEntity<ResponseMsg>(responsemsg, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ResponseMsg responsemsg = new ResponseMsg();
		responsemsg.setErrcode("102");
		String allFieldErrors = ex.getBindingResult().getFieldErrors().stream().map(e -> e.getDefaultMessage())
				.collect(Collectors.joining(","));
		responsemsg.setMessage(allFieldErrors);
		return new ResponseEntity<Object>(responsemsg, HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * @ExceptionHandler(ConstraintViolationException.class) public Map<String,
	 * String> handleConstraintViolation(ConstraintViolationException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * ex.getConstraintViolations().forEach(cv -> { errors.put("message",
	 * cv.getMessage()); errors.put("path", (cv.getPropertyPath()).toString()); });
	 * 
	 * return errors; }
	 */
	

	
}

