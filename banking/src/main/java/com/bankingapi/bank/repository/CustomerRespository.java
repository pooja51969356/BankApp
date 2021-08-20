package com.bankingapi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapi.bank.entity.CustomerEntity;

public interface CustomerRespository extends JpaRepository<CustomerEntity, String> {

	CustomerEntity findByPanNumber(String panNumber);

	CustomerEntity findByAdhaarNumber(String l);

	CustomerEntity findByEmailId(String email);

	CustomerEntity findByCustomerId(String customerId);

	CustomerEntity findByFirstName(String username);

}
