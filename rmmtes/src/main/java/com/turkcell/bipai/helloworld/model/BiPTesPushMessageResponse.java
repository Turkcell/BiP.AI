package com.turkcell.bipai.helloworld.model;


/**
 * 
 * TES servisi üzerinden gelen mesajın bilgilerini tutan cevap (response) sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class BiPTesPushMessageResponse {

	// TES servisinden gelen her cevabın id'sidir. Her cevaba tekil bir id atanır. 
	private String	txnid;
	// TES servisinden gelen cevabın sonuç kodudur. 
	private int		resultcode;
	
	public String getTxnid() {
		return txnid;
	}
	
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	
	public int getResultcode() {
		return resultcode;
	}
	
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}
	
}
