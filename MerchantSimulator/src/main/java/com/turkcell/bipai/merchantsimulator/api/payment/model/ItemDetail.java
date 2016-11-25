package com.turkcell.bipai.merchantsimulator.api.payment.model;

public class ItemDetail {
    private int itemId;
    private String itemName;
    private double itemPrice;
    private String currency;
    private Integer itemCount;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
	@Override
	public String toString() {
		return "Item Detail:[itemName:"+itemName+" itemId"+itemId+" itemPrice:"+itemPrice+" currency:"+currency+" itemCount:"+itemCount+"]"; 
	}

}
