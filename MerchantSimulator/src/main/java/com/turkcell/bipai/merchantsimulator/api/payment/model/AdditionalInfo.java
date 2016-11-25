package com.turkcell.bipai.merchantsimulator.api.payment.model;

public class AdditionalInfo {
	private String commitToken;

	public String getCommitToken() {
		return commitToken;
	}

	public void setCommitToken(String commitToken) {
		this.commitToken = commitToken;
	}
	
	@Override
	public String toString() {
		String result="";
		if(!getCommitToken().isEmpty()){
			result=result+"commitToken: "+getCommitToken();
		}
		return (result.isEmpty())?"":"Additional Info : "+result;
	}
}
