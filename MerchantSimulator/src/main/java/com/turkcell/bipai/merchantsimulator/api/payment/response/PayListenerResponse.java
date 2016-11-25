package com.turkcell.bipai.merchantsimulator.api.payment.response;

public class PayListenerResponse {
private String transactionId;
private String resultDesc;
private Integer resultCode;
public String getTransactionId() {
	return transactionId;
}
public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}
public String getResultDesc() {
	return resultDesc;
}
public void setResultDesc(String resultDesc) {
	this.resultDesc = resultDesc;
}
public Integer getResultCode() {
	return resultCode;
}
public void setResultCode(Integer resultCode) {
	this.resultCode = resultCode;
}

}
