package com.hilti.recommendation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "opportunity_score")
public class OpportunityScore {

	@Id
	private String id;
	private String accountId;
	private String accountSize;
	private String accountCatagory;
	private String categoryName;
	private String score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountSize() {
		return accountSize;
	}

	public void setAccountSize(String accountSize) {
		this.accountSize = accountSize;
	}

	public String getAccountCatagory() {
		return accountCatagory;
	}

	public void setAccountCatagory(String accountCatagory) {
		this.accountCatagory = accountCatagory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
