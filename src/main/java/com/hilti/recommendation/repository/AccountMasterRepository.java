package com.hilti.recommendation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hilti.recommendation.model.AccountMaster;

public interface AccountMasterRepository extends CrudRepository<AccountMaster, String> {
	AccountMaster findByAccountId(String accountId);

	List<AccountMaster> findByRegionAndMarketAndArea(String region, String market, String area);

	List<AccountMaster> findAllByOrderByScoreDesc();
	// List<AccountMaster> findByAccountId(String accountId);
}
