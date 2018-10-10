package com.hilti.recommendation.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilti.recommendation.model.AccountMaster;
import com.hilti.recommendation.model.AccountSalesMaster;
import com.hilti.recommendation.model.CustomResponse;
import com.hilti.recommendation.model.OpportunityMaster;
import com.hilti.recommendation.model.OpportunityScore;
import com.hilti.recommendation.repository.AccountMasterRepository;
import com.hilti.recommendation.repository.AccountSalesMasterRepository;
import com.hilti.recommendation.repository.OpportunityMasterRepository;
import com.hilti.recommendation.repository.OpportunityScoreRepository;

@Service
public class OpportunityMasterService {

	@Autowired
	private OpportunityMasterRepository opportunityMasterRepository;
	@Autowired
	private OpportunityScoreRepository opportunityScoreRepository;

	@Autowired
	private AccountMasterRepository accountMasterRepository;

	@Autowired
	private AccountSalesMasterRepository accountSalesMasterRepository;

	public List<OpportunityMaster> save() {
		List<AccountSalesMaster> accountSalesMasterList = accountSalesMasterRepository.findAll();

		Map<String, List<AccountSalesMaster>> groupByAccoundId = accountSalesMasterList.stream()
				.collect(Collectors.groupingBy(AccountSalesMaster::getAccountId));

		for (String accountId : groupByAccoundId.keySet()) {
			List<AccountSalesMaster> accountSalesMasters = groupByAccoundId.get(accountId);
			Map<String, List<AccountSalesMaster>> groupByCategory = accountSalesMasters.stream()
					.collect(Collectors.groupingBy(AccountSalesMaster::getCategory));
			for (String category : groupByCategory.keySet()) {
				Double sale = 0.0;
				OpportunityMaster opportunityMaster = new OpportunityMaster();
				List<AccountSalesMaster> accountSalesMastersByCategory = groupByCategory.get(category);
				for (AccountSalesMaster accountSalesMaster : accountSalesMastersByCategory) {
					sale = sale + accountSalesMaster.getSales();
				}

				AccountMaster accountMaster = accountMasterRepository.findByAccountId(accountId);
				if (accountMaster != null) {
					System.out.println("Account master found for the account Id : " + accountId);
					opportunityMaster.setCategory(category);
					opportunityMaster.setSales12(sale.toString());
					opportunityMaster.setAccountId(accountId);
					opportunityMaster.setSize(accountMaster.getSize());
					opportunityMaster.setAccountCategory(accountMaster.getCategory());
					opportunityMasterRepository.save(opportunityMaster);
				} else {
					System.out.println("Account master not found for the account Id : " + accountId);
				}

			}
		}
		return null;
	}

	public List<OpportunityMaster> findAll() {
		return opportunityMasterRepository.findAll();

	}

	public List<OpportunityMaster> findOne(String accountId) {
		return opportunityMasterRepository.findByAccountId(accountId);
	}

	public List<OpportunityScore> getOpportunityScore(String accountId) {

		List<OpportunityScore> opportunityScores = new ArrayList<>();

		List<OpportunityMaster> opportunityMasters = opportunityMasterRepository.findByAccountId(accountId);

		for (OpportunityMaster opportunityMaster : opportunityMasters) {

			List<OpportunityMaster> selectedList = new ArrayList<>();

			List<OpportunityMaster> temp = null;

			temp = opportunityMasterRepository.findByCategoryAndSales12AndSize(opportunityMaster.getCategory(),
					opportunityMaster.getSales12(), opportunityMaster.getSize());
			if (!temp.isEmpty()) {
				selectedList.addAll(temp);
			}
			temp = opportunityMasterRepository.findByCategoryAndSales12AndAccountCategory(
					opportunityMaster.getCategory(), opportunityMaster.getSales12(),
					opportunityMaster.getAccountCategory());
			if (!temp.isEmpty()) {
				selectedList.addAll(temp);
			}

			temp = opportunityMasterRepository.findByCategoryAndAccountCategoryAndSize(opportunityMaster.getCategory(),
					opportunityMaster.getAccountCategory(), opportunityMaster.getSize());
			if (!temp.isEmpty()) {
				selectedList.addAll(temp);
			}

			OpportunityScore opportunityScore = new OpportunityScore();
			List<OpportunityMaster> uniqueList = new ArrayList<>();
			Set<OpportunityMaster> uniqueSet = new HashSet<>();
			Map<String, OpportunityMaster> map = new LinkedHashMap<>();
			for (OpportunityMaster item : selectedList) {
				map.put(item.getAccountId(), item);
			}

			uniqueList.addAll(map.values());

			if (!uniqueList.isEmpty()) {
				for (OpportunityMaster opportunityMaster2 : uniqueList) {

					if (!opportunityMaster2.getAccountId().equals(opportunityMaster.getAccountId()))
						uniqueSet.add(opportunityMaster2);
				}
			}

			int highScore = 0;
			int lowScore = 0;
			int medium = 0;
			Iterator<OpportunityMaster> uniqueDataIterator = uniqueSet.iterator();
			while (uniqueDataIterator.hasNext()) {
				OpportunityMaster calculateOnOpportunity = uniqueDataIterator.next();
				if (Double.parseDouble(calculateOnOpportunity.getSales12()) == Double
						.parseDouble(opportunityMaster.getSales12())) {
					medium = medium + 1;
				} else if (Double.parseDouble(calculateOnOpportunity.getSales12()) < Double
						.parseDouble(opportunityMaster.getSales12())) {
					lowScore = lowScore + 1;
				} else if (Double.parseDouble(calculateOnOpportunity.getSales12()) > Double
						.parseDouble(opportunityMaster.getSales12())) {
					highScore = highScore + 1;
				}
			}

			opportunityScore.setAccountId(accountId);
			opportunityScore.setCategoryName(opportunityMaster.getCategory());
			opportunityScore.setAccountSize(opportunityMaster.getSize());
			opportunityScore.setAccountCatagory(opportunityMaster.getAccountCategory());

			if ((highScore == 0 && medium == 0 && lowScore == 0) || (highScore == medium && highScore == lowScore)) {
				opportunityScore.setScore("M");
			} else if (highScore > medium && highScore > lowScore) {
				opportunityScore.setScore("H");
			} else if (medium > highScore && medium > lowScore) {
				opportunityScore.setScore("M");
			} else if (lowScore > highScore && lowScore > medium) {
				opportunityScore.setScore("L");
			} else if (highScore == medium) {
				opportunityScore.setScore("H");
			} else {
				opportunityScore.setScore("M");
			}
			opportunityScores.add(opportunityScore);
		}
		return opportunityScores;
	}

	public List<OpportunityScore> saveopportunityScore() {
		List<OpportunityMaster> opportunityMasters = opportunityMasterRepository.findAll();

		Set<String> ids = opportunityMasters.stream().map((master) -> master.getAccountId()).collect(toSet());
		List<OpportunityScore> opportunityScores = new ArrayList<>();
		for (String accoundId : ids) {
			List<OpportunityScore> scoresById = getOpportunityScore(accoundId);
			for (OpportunityScore score : scoresById) {
				opportunityScoreRepository.save(score);
			}
			opportunityScores.addAll(scoresById);
		}
		return opportunityScores;
	}

	public CustomResponse getConfig() {
		int small = 0, medium = 0, large = 0, decline = 0, stable = 0, growth = 0;
		List<OpportunityScore> scores = opportunityScoreRepository.findAll();
		Map<String, List<OpportunityScore>> map = scores.stream().collect(groupingBy(OpportunityScore::getAccountId));
		for (String accountId : map.keySet()) {
			List<OpportunityScore> opportunityScores = map.get(accountId);
			for (OpportunityScore opportunityScore : opportunityScores) {
				if ("H".equalsIgnoreCase(opportunityScore.getScore())) {
					if ("S".equalsIgnoreCase(opportunityScore.getAccountSize())) {
						++small;
					}
					if ("M".equalsIgnoreCase(opportunityScore.getAccountSize())) {
						++medium;
					}
					if ("L".equalsIgnoreCase(opportunityScore.getAccountSize())) {
						++large;
					}
					if ("Decline".equalsIgnoreCase(opportunityScore.getAccountCatagory())) {
						++decline;
					}
					if ("Stable".equalsIgnoreCase(opportunityScore.getAccountCatagory())) {
						++stable;
					}
					if ("Growth".equalsIgnoreCase(opportunityScore.getAccountCatagory())) {
						++growth;
					}
					break;
				}
			}

		}
		return new CustomResponse(small, medium, large, decline, stable, growth);
	}
}
