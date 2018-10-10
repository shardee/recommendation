package com.hilti.recommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilti.recommendation.model.ModelData;
import com.hilti.recommendation.repository.ConfigRepository;

@Service
public class HiltiRecommendationConfigurationService {

	@Autowired
	private ConfigRepository configRepository;

	public ModelData addConfiguration(ModelData modelData) {

		return configRepository.save(modelData);

	}

	public ModelData findConfiguration(String id) {

		return configRepository.findOne(id);
	}
	public List<ModelData> getData() {
		return (List<ModelData>)configRepository.findAll();
	}

}
