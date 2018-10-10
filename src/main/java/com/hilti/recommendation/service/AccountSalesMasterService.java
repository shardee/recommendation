package com.hilti.recommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilti.recommendation.model.AccountSalesMaster;
import com.hilti.recommendation.repository.AccountSalesMasterRepository;

@Service
public class AccountSalesMasterService {

	@Autowired
	private AccountSalesMasterRepository accountSalesMasterRepository;

	public List<AccountSalesMaster> save(List<AccountSalesMaster> accountSalesMasters) {
		for (AccountSalesMaster accountSalesMaster : accountSalesMasters) {
			accountSalesMasterRepository.save(accountSalesMaster);
		}
		return accountSalesMasters;

	}

	public List<AccountSalesMaster> getData() {
		return accountSalesMasterRepository.findAll();
	}

	public List<AccountSalesMaster> findOne(String accountId) {
		// List<AccountSalesMaster> salesMasters = new ArrayList<>();
		// salesMasters.add(accountSalesMasterRepository.findOne(accountId));
		return accountSalesMasterRepository.findByAccountId(accountId);
	}

	public Double findSalesByAccountId(String accountId) throws Exception {
		AccountSalesMaster accountSalesMaster = accountSalesMasterRepository.findSalesById(accountId);
		if (accountSalesMaster != null) {
			return accountSalesMaster.getSales();
		}
		throw new Exception("No sales master found by Accound Id : " + accountId);
	}
}
