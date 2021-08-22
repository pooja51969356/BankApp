package com.bankingapi.bank.serviceImpl;



import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankingapi.bank.dto.RegisterAccountRequestDto;
import com.bankingapi.bank.dto.RegisterAccountResponseDto;
import com.bankingapi.bank.dto.UserLoginResponseDto;
import com.bankingapi.bank.entity.AccountEntity;
import com.bankingapi.bank.entity.CustomerEntity;
import com.bankingapi.bank.exception.InavalidAccountException;
import com.bankingapi.bank.repository.AccountHistoryRepositroy;
import com.bankingapi.bank.repository.CustomerRespository;
import com.bankingapi.bank.service.UserRegistrationService;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	CustomerRespository customerRespository;
	
	@Autowired
	AccountHistoryRepositroy accountHistoryRepositroy;
	
	@Autowired
	EntityManagerFactory emf;	
	

	@Override
	public UserLoginResponseDto createAccount(RegisterAccountRequestDto registerAccountRequestDto) {
		
		

		//check basic validation before save customer detail
		if(registerAccountRequestDto==null) {    
			throw new InavalidAccountException("Please check Details");
		}

		boolean flag = true;
		String message = "Registration Succesful";
		
		
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


		CustomerEntity responseMas=customerRespository.save(prepareCustomerDetail(registerAccountRequestDto));

		return prepareUserLoginResponseDto(responseMas,message,flag);	








	}


	private CustomerEntity prepareCustomerDetail(RegisterAccountRequestDto registerAccountRequestDto) {
		
		CustomerEntity customerMas=new CustomerEntity();
		customerMas.setCustomerId(generateMaxcustomerId(String.valueOf(LocalDate.now().getYear())));
		customerMas.setPassword(customerPasswordGeneration(registerAccountRequestDto.getFirstName(),registerAccountRequestDto.getPanNumber()));
		customerMas.setFirstName(registerAccountRequestDto.getFirstName());
		customerMas.setLastName(registerAccountRequestDto.getLastName());
		customerMas.setGender(registerAccountRequestDto.getGender());
		customerMas.setAge(registerAccountRequestDto.getAge());
		customerMas.setEmailId(registerAccountRequestDto.getEmail());
		customerMas.setDob(registerAccountRequestDto.getDob());
		customerMas.setAdhaarNumber(registerAccountRequestDto.getAdhaarNumber());
		customerMas.setPanNumber(registerAccountRequestDto.getPanNumber());
		customerMas.setAddress(registerAccountRequestDto.getAddress());	
//		customerMas.setStatus(true);
//		customerMas.setAuthorizationStatus(true);
		customerMas.setAccountDetail(prepareAccountDetail());
		
		
		return customerMas;
	}
	public BigInteger generationAccountNumber(String bankCode) {
		
		//generate ac number on a behalf of ifscCode,branchCode
		
		boolean flag=true;
		BigInteger accountNumber=null;
		
		
		while(flag) {
			
			String randomNumber=String.valueOf(100000 +new Random().nextInt(900000));
			
			BigInteger generatedAccountNumber =new BigInteger(bankCode+randomNumber);
			
			AccountEntity accountEntity=accountHistoryRepositroy.findByAccountNumber(accountNumber);
			
			if(accountEntity!=null) {

				if(accountEntity.getAccountNumber()==null || accountEntity.getAccountNumber().compareTo(generatedAccountNumber)==0) {
					accountNumber=generatedAccountNumber;
					flag=false;
				}
				
			}else {
				accountNumber=generatedAccountNumber;
				flag=false;
			}
					
			
		}
		
		System.out.println(false);
		
		
		return accountNumber;
	}
	
	
	
	
	public String  generateMaxcustomerId(String year) {
		
		String maxCustomerId=null;
		
		
		EntityManager em=emf.createEntityManager();
		
//		StringBuilder builder=new StringBuilder()
//				.append("SELECT MAX(   cast (SUBSTRING (customer_id,6,11) as NUMERIC) )+1 as customer_id FROM customer_details "
//						+ "WHERE SUBSTRING (customer_id,0,5)='"+year+"'");
		
		StringBuilder builder=new StringBuilder()
				.append("SELECT MAX(\r\n"
						+ "	(CASE WHEN (SUBSTRING (customer_id,6,11) is null )\r\n"
						+ "	 THEN 0\r\n"
						+ "	 ELSE (\r\n"
						+ "		 cast (SUBSTRING (customer_id,6,11) as NUMERIC)\r\n"
						+ "	 ) END\r\n"
						+ "	)\r\n"
						+ ")+1 as customer_id FROM customer_details "
						+ "WHERE SUBSTRING (customer_id,0,5)='"+year+"'");		
		
		
		
		
		
		Query query=em.createNativeQuery(builder.toString());
				
		
		
		@SuppressWarnings("unchecked")
		List<Object> rows=query.getResultList();		//Need to handle SqlExceptionHelper
		
		em.close();
		
		
		String maxToken=null;
		
		if(rows.get(0)!=null)		
			maxToken=String.valueOf(rows.get(0));			//need to handle exception handle 
		else
			maxToken="000001";
		
		
		
		if(maxToken.length()<2) {
			maxCustomerId = "00000"+maxToken;
		}else if(maxToken.length()<3) {
			maxCustomerId = "0000"+maxToken;
		}else if(maxToken.length()<4) {
			maxCustomerId = "000"+maxToken;
		}else if(maxToken.length()<5) {
			maxCustomerId = "00"+maxToken;
		}else if(maxToken.length()<6) {
			maxCustomerId = "0"+maxToken;
		}else {
			maxCustomerId = maxToken;
		}

		
		
		if(maxCustomerId==null || maxCustomerId.length()<1) {
			maxCustomerId=year+"000001";
		}else
			maxCustomerId=year+maxCustomerId;
		
		
		return maxCustomerId;
	}
	
	////Password generate
	public static String customerPasswordGeneration(String clientFirstName,String panNumber) {
		
		//String password = PasswordUtil.generatePassword();
		
		if(clientFirstName.length()>=4)
			clientFirstName=clientFirstName.substring(0,4);
		
		
		String password=clientFirstName.toLowerCase()+panNumber.substring(0,4).toLowerCase()+new Random().nextInt(100);
		
		logger.info("generated new password: "+password);
		
		return password;
	}
	private List<AccountEntity> prepareAccountDetail() {
		
		AccountEntity accountMas=new AccountEntity();
		accountMas.setAccountNumber(generationAccountNumber("1107"));
		accountMas.setAccountType("SAVING");
		accountMas.setBranchAddress("Gomti Nagar Lucknow");
		accountMas.setIfscCode("SBIIN0001");
		accountMas.setOpeningBalance(10000.0);
		
		List<AccountEntity> list=new ArrayList<>();
		list.add(accountMas);
		
		return List.of(accountMas);
		
	}
///Success Password andCustomerId Response
	private UserLoginResponseDto prepareUserLoginResponseDto(CustomerEntity responseMas,String message,Boolean flag) {
		
		UserLoginResponseDto accountResponseDto=new UserLoginResponseDto();	
		
		accountResponseDto.setRegistrationStatus(flag);
		accountResponseDto.setResponseMessage(message);
		
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
