package com.bankingapi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapi.bank.entity.CustomerEntity;

public interface CustomerRespository extends JpaRepository<CustomerEntity, Integer> {

	CustomerEntity findByPanNumber(String panNumber);

}
