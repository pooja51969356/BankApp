package com.bankingapi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapi.bank.entity.TransactionEntity;

public interface FundTransferRepository extends JpaRepository<TransactionEntity, Integer>{

}
