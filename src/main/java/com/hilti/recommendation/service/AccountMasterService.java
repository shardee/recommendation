package com.hilti.recommendation.service;

import static com.hilti.recommendation.constant.HiltiRecommendationConstants.DECLINE;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.GROWTH;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.LARGE;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.MEDIUM;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.SMALL;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.STABLE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilti.recommendation.model.AccountMaster;
import com.hilti.recommendation.model.AccountSalesMaster;
import com.hilti.recommendation.model.ModelData;
import com.hilti.recommendation.repository.AccountMasterRepository;
import com.hilti.recommendation.repository.AccountSalesMasterRepository;
import com.hilti.recommendation.repository.ConfigRepository;

@Service
public class AccountMasterService {

	@Autowired
	private AccountMasterRepository accountMasterRepository;

	@Autowired
	private ConfigRepository configRepository;

	@Autowired
	private AccountSalesMasterRepository accountSalesMasterRepository;

	public List<AccountMaster> save(List<AccountMaster> accountMasters) {
		return (List<AccountMaster>) accountMasterRepository.save(accountMasters);
	}

	public AccountMaster findOne(String accountId) {
		return accountMasterRepository.findByAccountId(accountId);
	}

	public List<AccountMaster> getData() {
		List<AccountMaster> response = new ArrayList<>();
		List<AccountMaster> data = accountMasterRepository.findAllByOrderByScoreDesc();
		for (int i = 0; i < data.size(); i++) {
			data.get(i).setRank(String.valueOf(i + 1));
			response.add(data.get(i));
		}
		return response;
	}

	public List<AccountMaster> config() {
		List<AccountMaster> responseData = new ArrayList<>();
		List<ModelData> modelDatas = configRepository.findAll();
		List<AccountSalesMaster> salesMasters = accountSalesMasterRepository.findAll();

		for (ModelData modelData : modelDatas) {

			List<AccountMaster> accountMasters = accountMasterRepository
					.findByRegionAndMarketAndArea(modelData.getRegion(), modelData.getMarket(), modelData.getArea());
			for (AccountMaster accountMaster : accountMasters) {
				List<AccountSalesMaster> accountsById = findAccountsById(accountMaster.getAccountId(), salesMasters);
				Double sales12 = calculateSales12(accountsById);
				System.out.println("Total Sales for account Id " + accountMaster.getAccountId() + " is - " + sales12);
				accountMaster.setSales12(sales12);
				accountMaster.setScore(calculateScore(modelData, accountMaster));
				accountMasterRepository.save(accountMaster);
				responseData.add(accountMaster);
			}
		}
		return responseData;
	}

	private Double calculateScore(ModelData modelData, AccountMaster accountMaster) {

		int sizeValue = 0;
		int categoryValue = 0;

		if (accountMaster.getSize().equalsIgnoreCase(SMALL)) {
			sizeValue = Integer.parseInt(modelData.getSmall());
		} else if (accountMaster.getSize().equalsIgnoreCase(MEDIUM)) {
			sizeValue = Integer.parseInt(modelData.getMedium());
		} else if (accountMaster.getSize().equalsIgnoreCase(LARGE)) {
			sizeValue = Integer.parseInt(modelData.getLarge());
		}

		if (accountMaster.getCategory().equalsIgnoreCase(GROWTH)) {
			categoryValue = Integer.parseInt(modelData.getGrowth());
		} else if (accountMaster.getCategory().equalsIgnoreCase(STABLE)) {
			categoryValue = Integer.parseInt(modelData.getStable());
		} else if (accountMaster.getCategory().equalsIgnoreCase(DECLINE)) {
			categoryValue = Integer.parseInt(modelData.getDecline());
		}

		// score/100000
		return ((Double.parseDouble(modelData.getCategoryWeight()) * categoryValue)

				+ (Double.parseDouble(modelData.getSizeWeight()) * sizeValue)

				+ (Double.parseDouble(modelData.getSalesWeight()) * accountMaster.getSales12())) / 100000.0d;

	}

	private Double calculateSales12(List<AccountSalesMaster> accountsById) {
		return accountsById.stream().mapToDouble(AccountSalesMaster::getSales).sum();
	}

	private List<AccountSalesMaster> findAccountsById(String accountId, List<AccountSalesMaster> accountSalesMasters) {
		return accountSalesMasters.stream().filter((account) -> account.getAccountId() != null)
				.filter((account) -> account.getAccountId().equals(accountId)).collect(Collectors.toList());
	}
}
