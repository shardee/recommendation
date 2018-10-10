package com.hilti.recommendation.repository;

import com.hilti.recommendation.model.AccountSalesMaster;

public interface AccountSalesMasterCustomRepository {

	AccountSalesMaster findSalesById(String accountId);
}
