package com.hilti.recommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilti.recommendation.model.AccountMaster;
import com.hilti.recommendation.service.AccountMasterService;

@RestController
@RequestMapping(value = "hilti/account/master", produces = "application/json")
public class AccountMasterController {

	@Autowired
	private AccountMasterService accountMasterService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public List<AccountMaster> save(@RequestBody List<AccountMaster> accountMasters) {
		return accountMasterService.save(accountMasters);
	}

	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public List<AccountMaster> config() {
		return accountMasterService.config();
	}

	@RequestMapping(value = "/data", method = RequestMethod.POST)
	public List<AccountMaster> getData() {
		return accountMasterService.getData();
	}

	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public AccountMaster findOne(@RequestParam("accountId") String accountId) {
		return accountMasterService.findOne(accountId);
	}

}
