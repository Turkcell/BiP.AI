package com.turkcell.bipai.merchantsimulator.api.payment.response;

public class PayServiceResponse {
	private String transactionId;
	private Integer resultCode;
	private String resultDesc;



	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	
	@Override
	public String toString() {
		return "PayServiceResponse transactionId: ("+transactionId+") resultCode: ("+resultCode+") resultDesc: ("+resultDesc+")";
	}
}
