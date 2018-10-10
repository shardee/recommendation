package com.hilti.recommendation.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_data")
public class ModelData implements Persistable<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;	   
	private String market;
	@Indexed(unique = true)
	private String area;
	private String region;
	private String salesWeight;
	private String sizeWeight;
	private String categoryWeight;
	private String growth;
	private String decline;
	private String stable;
	private String small;
	private String medium;
	private String large;
	@LastModifiedBy
	private String updatedBy;
	@LastModifiedDate
    private Date lastUpdated;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSalesWeight() {
		return salesWeight;
	}
	public void setSalesWeight(String salesWeight) {
		this.salesWeight = salesWeight;
	}
	public String getSizeWeight() {
		return sizeWeight;
	}
	public void setSizeWeight(String sizeWeight) {
		this.sizeWeight = sizeWeight;
	}
	public String getCategoryWeight() {
		return categoryWeight;
	}
	public void setCategoryWeight(String categoryWeight) {
		this.categoryWeight = categoryWeight;
	}
	public String getGrowth() {
		return growth;
	}
	public void setGrowth(String growth) {
		this.growth = growth;
	}
	public String getDecline() {
		return decline;
	}
	public void setDecline(String decline) {
		this.decline = decline;
	}
	public String getStable() {
		return stable;
	}
	public void setStable(String stable) {
		this.stable = stable;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getLarge() {
		return large;
	}
	public void setLarge(String large) {
		this.large = large;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	
}
