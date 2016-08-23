
package com.turkcell.bipai.helloworld.model;

/**
 * 
 * REX servisi üzerinden gelen mesajın cevabını tutan sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class BiPRexOutput {

	private String		transactionId;
	private Composition	composition;

	public BiPRexOutput() {
		super();
	}

	public BiPRexOutput(String transactionId) {
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
