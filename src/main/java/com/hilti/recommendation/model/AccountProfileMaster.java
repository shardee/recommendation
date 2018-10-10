package com.hilti.recommendation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_profile_master")
public class AccountProfileMaster {

	@Id
	private String id;

	private String accountId;
	
	private String accountSales;
	private String accountCategory;
	private String accountSize ;
	private String weight;
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
	public String getAccountSales() {
		return accountSales;
	}
	public void setAccountSales(String accountSales) {
		this.accountSales = accountSales;
	}
	public String getAccountCategory() {
		return accountCategory;
	}
	public void setAccountCategory(String accountCategory) {
		this.accountCategory = accountCategory;
	}
	public String getAccountSize() {
		return accountSize;
	}
	public void setAccountSize(String accountSize) {
		this.accountSize = accountSize;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
	
	
}
