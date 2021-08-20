package com.bankingapi.bank.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapi.bank.entity.BenificiaryEntity;

public interface BenificiaryRepository extends JpaRepository<BenificiaryEntity, BigInteger> {

	BenificiaryEntity findByName(String name);

	BenificiaryEntity findByAccountNo(BigInteger accountNo);

}
