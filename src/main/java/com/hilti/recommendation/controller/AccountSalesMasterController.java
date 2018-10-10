package com.hilti.recommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilti.recommendation.model.AccountSalesMaster;
import com.hilti.recommendation.service.AccountSalesMasterService;

@RestController
@RequestMapping(value = "hilti/account/master/sales", produces = "application/json")
public class AccountSalesMasterController {

	@Autowired
	private AccountSalesMasterService accountSalesMasterService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public List<AccountSalesMaster> save(@RequestBody List<AccountSalesMaster> accountSalesMasters) {
		return accountSalesMasterService.save(accountSalesMasters);
	}

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public List<AccountSalesMaster> getData() {
		return accountSalesMasterService.getData();
	}

	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public List<AccountSalesMaster> findOne(@RequestParam("accountId") String accountId) {
		return accountSalesMasterService.findOne(accountId);
	}

	@GetMapping("/{accountId}")
	public Double findSalesByAccountId(@PathVariable("accountId") String accountId) throws Exception {
		return accountSalesMasterService.findSalesByAccountId(accountId);
	}

}