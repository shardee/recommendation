package com.hilti.recommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hilti.recommendation.model.AccountProfileMaster;
import com.hilti.recommendation.service.AccountProfileMasterService;

@RestController
@RequestMapping(value = "hilti/account/master/profile", produces = "application/json")
public class AccountProfileMasterController {

	@Autowired
	private AccountProfileMasterService accountProfileMasterService ;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public List<AccountProfileMaster> save(@RequestBody List<AccountProfileMaster> accountProfileMasters) {
		return accountProfileMasterService.save(accountProfileMasters);
	}
}
