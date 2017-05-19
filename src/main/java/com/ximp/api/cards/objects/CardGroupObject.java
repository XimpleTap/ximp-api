package com.ximp.api.cards.objects;

public class CardGroupObject {

	private int id;
	private double cardPrice;
	private double preloadedAmount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
}
