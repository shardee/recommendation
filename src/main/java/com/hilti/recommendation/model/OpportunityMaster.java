package com.hilti.recommendation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "opportunity_master")
public class OpportunityMaster {

	@Id
	private String id;
	private String accountId;
	private String category;
	private String opportunityLabel;
	private String sales12;
	private String accountCategory;
	private String size;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSales12() {
		return sales12;
	}

	public void setSales12(String sales12) {
		this.sales12 = sales12;
	}

	public String getAccountCategory() {
		return accountCategory;
	}

	public void setAccountCategory(String accountCategory) {
		this.accountCategory = accountCategory;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getOpportunityLabel() {
		return opportunityLabel;
	}

	public void setOpportunityLabel(String opportunityLabel) {
		this.opportunityLabel = opportunityLabel;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 7 * hash + this.sales12.hashCode();
		hash = 7 * hash + this.accountId.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpportunityMaster other = (OpportunityMaster) obj;
		if (this.accountId == other.accountId)
			return true;
		else
			return false;
	}
}
