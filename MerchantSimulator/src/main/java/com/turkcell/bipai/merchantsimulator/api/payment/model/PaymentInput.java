package com.turkcell.bipai.merchantsimulator.api.payment.model;

import java.util.List;

public class PaymentInput {

	protected int resultCode;
	protected String resultDesc;
	protected String transactionId;
	protected AdditionalInfo additionalInfo;

	protected Address shippingAddress;
	protected Address billingAddress;
	protected String ebillMailAddress;
	protected String paymentMethod;
	protected List<ItemDetail> itemList;
	protected double totalAmount;
	protected String orderDate;
	protected String currency;
	private int channelId;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public AdditionalInfo getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(AdditionalInfo additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getEbillMailAddress() {
		return ebillMailAddress;
	}

	public void setEbillMailAddress(String ebillMailAddress) {
		this.ebillMailAddress = ebillMailAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public List<ItemDetail> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemDetail> itemList) {
		this.itemList = itemList;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	@Override
	public String toString() {
		return "PaymentServiceCallbackRequest [resultCode=" + resultCode + ", resultDesc=" + resultDesc
				+ ", transactionId=" + transactionId + ", additionalInfo=" + additionalInfo + ", shippingAddress="
				+ shippingAddress + ", billingAddress=" + billingAddress + ", ebillMailAddress=" + ebillMailAddress
				+ ", paymentMethod=" + paymentMethod + ", itemList=" + itemList + ", totalAmount=" + totalAmount
				+ ", orderDate=" + orderDate + ", currency=" + currency + "]";
	}

}
