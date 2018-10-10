package com.hilti.recommendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hilti.recommendation.model.OpportunityScore;

public interface OpportunityScoreRepository extends MongoRepository<OpportunityScore, String> {

}
