package com.bankingapi.bank.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapi.bank.entity.BeneficiaryEntity;

public interface BeneficiaryRepository extends JpaRepository<BeneficiaryEntity, BigInteger> {

	
	Optional<BeneficiaryEntity> findByAccountNo(BigInteger accountNo);

}
