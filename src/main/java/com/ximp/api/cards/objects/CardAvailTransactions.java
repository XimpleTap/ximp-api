package com.ximp.api.cards.objects;

import java.util.Date;

public class CardAvailTransactions {

	private String productDescription;
	private double oldBalance;
	private double newBalance;
	private int itemsCount;
	private double totalPrice;
	private Date transactionTime;
	
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getOldBalance() {
		return oldBalance;
	}
	public void setOldBalance(double oldBalance) {
		this.oldBalance = oldBalance;
	}
	public double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}
	public int getItemsCount() {
		return itemsCount;
	}
	public void setItemsCount(int itemsCount) {
		this.itemsCount = itemsCount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
}
