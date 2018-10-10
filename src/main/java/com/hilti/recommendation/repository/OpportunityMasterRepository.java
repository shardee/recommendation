package com.hilti.recommendation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hilti.recommendation.model.OpportunityMaster;

public interface OpportunityMasterRepository extends MongoRepository<OpportunityMaster, String>{

	List<OpportunityMaster> findByAccountId(String accountId);
	
	List<OpportunityMaster> findByCategory(String category) ;
	
	List<OpportunityMaster> findByCategoryAndSales12AndSize(String category , String sales12 , String size );
	
	List<OpportunityMaster> findByCategoryAndSales12AndAccountCategory(String category , String sales12 , String accountCategory );
	
	List<OpportunityMaster> findByCategoryAndAccountCategoryAndSize(String category , String accountCategory , String size );
	
}
