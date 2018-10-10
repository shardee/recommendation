package com.hilti.recommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hilti.recommendation.model.ModelData;
import com.hilti.recommendation.service.HiltiRecommendationConfigurationService;

@RestController
@RequestMapping(value = "hilti/config", produces = "application/json")
public class HiltiRecommendationConfigurationController {

	@Autowired
	private HiltiRecommendationConfigurationService hiltiRecommendationConfigurationService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelData addConfiguration(@RequestBody ModelData modelData) {
		return hiltiRecommendationConfigurationService.addConfiguration(modelData);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelData findConfiguration(String accountId) {
		return hiltiRecommendationConfigurationService.findConfiguration(accountId);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<ModelData> getData() {
		return hiltiRecommendationConfigurationService.getData();
	}
}
