package com.turkcell.bipai.merchantsimulator.api.payment.request;

public class PayCommitRequest {
	private String transactionId;//Servisimizin atamış olduğu unique transaction id yi tutar.
	private String msisdn;		 //Açık numarayı tutar.
	private String commitToken; //Commit request gönderirken payment servisten aldığımız commit token ı tutar.
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
	public String getCommitToken() {
		return commitToken;
	}
	public void setCommitToken(String commitToken) {
		this.commitToken = commitToken;
	}
	public Integer getChanneId() {
		return channeId;
	}
	public void setChanneId(Integer channeId) {
		this.channeId = channeId;
	}
	private Integer channeId;
}
