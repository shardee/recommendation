package com.hilti.recommendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hilti.recommendation.model.ModelData;


public interface ConfigRepository extends MongoRepository<ModelData , String>{
	
}
