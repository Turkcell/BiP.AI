package com.turkcell.bipai.helloworld.api.rex.request;

import com.turkcell.bipai.helloworld.api.rex.model.Composition;

/**
 * 
 * REX servisi üzerinden gelen mesajın cevabını tutan sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class RexRequest {

	private String		transactionId;
	private Composition	composition;

	public RexRequest() {
		super();
	}

	public RexRequest(String transactionId) {
		this();
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Composition getComposition() {
		return composition;
	}

	public void setComposition(Composition composition) {
		this.composition = composition;
	}

}
