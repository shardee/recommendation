package com.hilti.recommendation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hilti.recommendation.model.AccountSalesMaster;

public interface AccountSalesMasterRepository
		extends MongoRepository<AccountSalesMaster, String>, AccountSalesMasterCustomRepository {

	List<AccountSalesMaster> findByAccountId(String accountId);
}
