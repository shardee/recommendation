package com.hilti.recommendation.model;

public class CustomResponse {

	private int small;
	private int medium;
	private int large;
	private int decline;
	private int stable;
	private int growth;

	public int getSmall() {
		return small;
	}

	public void setSmall(int small) {
		this.small = small;
	}

	public int getMedium() {
		return medium;
	}

	public void setMedium(int medium) {
		this.medium = medium;
	}

	public int getLarge() {
		return large;
	}

	public void setLarge(int large) {
		this.large = large;
	}

	public int getDecline() {
		return decline;
	}

	public void setDecline(int decline) {
		this.decline = decline;
	}

	public int getStable() {
		return stable;
	}

	public void setStable(int stable) {
		this.stable = stable;
	}

	public int getGrowth() {
		return growth;
	}

	public void setGrowth(int growth) {
		this.growth = growth;
	}

	public CustomResponse(int small, int medium, int large, int decline, int stable, int growth) {
		super();
		this.small = small;
		this.medium = medium;
		this.large = large;
		this.decline = decline;
		this.stable = stable;
		this.growth = growth;
	}

}
