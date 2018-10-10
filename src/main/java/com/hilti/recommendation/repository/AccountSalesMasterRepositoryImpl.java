package com.hilti.recommendation.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.hilti.recommendation.model.AccountSalesMaster;

public class AccountSalesMasterRepositoryImpl implements AccountSalesMasterCustomRepository {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public AccountSalesMaster findSalesById(String accountId) {
		MatchOperation matchOperation = Aggregation.match(Criteria.where("accountId").is(accountId));
		GroupOperation groupOperation = Aggregation.group("accountId").sum("sales").as("sales");
		Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation);
		AggregationResults<AccountSalesMaster> master = mongoOperations.aggregate(aggregation, "account_sales_master",
				AccountSalesMaster.class);
		List<AccountSalesMaster> account = master.getMappedResults();
		if (!account.isEmpty()) {
			return account.get(0);
		} else {
			return null;
		}
	}

}
