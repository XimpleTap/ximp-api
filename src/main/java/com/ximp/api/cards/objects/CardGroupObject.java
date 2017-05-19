package com.ximp.api.cards.objects;

public class CardGroupObject {

	private double cardPrice;
	private double preloadedAmount;
	private int businessId;
	public double getCardPrice() {
		return cardPrice;
	}
	public void setCardPrice(double cardPrice) {
		this.cardPrice = cardPrice;
	}
	public double getPreloadedAmount() {
		return preloadedAmount;
	}
	public void setPreloadedAmount(double preloadedAmount) {
		this.preloadedAmount = preloadedAmount;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
}
