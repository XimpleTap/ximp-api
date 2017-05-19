package com.ximp.api.products.objects;

import java.util.List;

public class AcquireProductRequest {
	private String cardNumber;
	private int businessId;
	private List<ProductToAcquire> productsToAcquire;
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public List<ProductToAcquire> getProductsToAcquire() {
		return productsToAcquire;
	}
	public void setProductsToAcquire(List<ProductToAcquire> productsToAcquire) {
		this.productsToAcquire = productsToAcquire;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
