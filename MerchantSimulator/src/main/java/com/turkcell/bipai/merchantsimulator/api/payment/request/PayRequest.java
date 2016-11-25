package com.turkcell.bipai.merchantsimulator.api.payment.request;

import java.util.List;

import com.turkcell.bipai.merchantsimulator.api.payment.model.Item;

public class PayRequest {

	private String transactionId; // Sipariş için üretilen unique id yi tutar.
	private String msisdn;		  // Sipariş veren kişinin açık numarasını tutar.
	private Integer channelId;    // Payment kanal bilgisini tutar.
	private String paymentMethod; 
	private String isDeliverable;
	private List<Item> itemList;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getIsDeliverable() {
		return isDeliverable;
	}
	public void setIsDeliverable(String isDeliverable) {
		this.isDeliverable = isDeliverable;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
}
