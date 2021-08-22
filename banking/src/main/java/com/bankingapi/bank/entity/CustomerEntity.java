package com.bankingapi.bank.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "customer_details")
public class CustomerEntity {

	@Id
	@Column(name = "customer_id")
    private String customerId;
	@Column(name = "address")
    private String address;
	@Column(name = "age")
    private int age;
	@Column(name = "dob")
	//@Temporal(TemporalType.DATE)
	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyy-MM-dd")
    private Date dob;
	@Column(name = "email_id")
    private String emailId;
	@Column(name = "first_name")
    private String firstName;
	@Column(name = "gender")
    private String gender;
	@Column(name = "last_name")
    private String lastName;
	@Column(name = "adhaar_number")
    private String adhaarNumber;
	@Column(name = "pan_number")
    private String panNumber;
	@Column(name = "phone_number")
    private String phoneNumber;
	@Column(name = "password")
    private String password;

	private boolean status;
	
	@Column(name = "authorization_status")
	private boolean authorizationStatus;
	

	@OneToMany(targetEntity = AccountEntity.class ,cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id",referencedColumnName = "customer_id" )
	private List<AccountEntity> accountDetail;



	
	
	/*
	 * @OneToOne(targetEntity = CustomerReigistrationEntity.class ,cascade =
	 * CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "customer_id",referencedColumnName = "customer_id" )
	 * private CustomerReigistrationEntity loginDetails;
	 */
}
