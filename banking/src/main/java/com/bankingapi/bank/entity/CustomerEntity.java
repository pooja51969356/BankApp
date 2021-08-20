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
	@SequenceGenerator(name="customer_details_customer_id_seq",sequenceName="customer_details_customer_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="customer_details_customer_id_seq")
	@Column(name = "customer_id")
    private int customerId;
	@Column(name = "address")
    private String address;
	@Column(name = "age")
    private int age;
	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
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
	
	

	@OneToMany(targetEntity = AccountEntity.class ,cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id",referencedColumnName = "customer_id" )
	private List<AccountEntity> accountDetail;
    
}
