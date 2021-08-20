package com.bankingapi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapi.bank.entity.CustomerEntity;

public interface UserRepository extends JpaRepository<CustomerEntity, String>{

	CustomerEntity findByFirstName(String username);

}
