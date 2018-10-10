package com.hilti.recommendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hilti.recommendation.model.AccountProfileMaster;

public interface AccountProfileMasterRepository extends MongoRepository<AccountProfileMaster	, String>{

	
	AccountProfileMaster findByAccountId(String accountId) ;
}
