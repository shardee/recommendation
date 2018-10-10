package com.hilti.recommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilti.recommendation.model.AccountProfileMaster;
import com.hilti.recommendation.repository.AccountProfileMasterRepository;
@Service
public class AccountProfileMasterService {

	
	@Autowired
	private AccountProfileMasterRepository accountProfileMasterRepository;

	public List<AccountProfileMaster> save(List<AccountProfileMaster> accountProfileMasters) {
		for (AccountProfileMaster accountProfileMaster : accountProfileMasters) {
				accountProfileMasterRepository.save(accountProfileMaster);
		}
		return accountProfileMasters;

	}
}
