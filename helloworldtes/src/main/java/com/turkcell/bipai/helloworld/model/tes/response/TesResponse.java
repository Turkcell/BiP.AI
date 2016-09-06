package com.turkcell.bipai.helloworld.model.tes.response;


/**
 * 
 * TES servisi üzerinden gelen mesajın bilgilerini tutan cevap (response) üst sınıfıdır. 
 * Mesajın tek kullanıcı, çok kullanıcı veya çok kulalnıcıya farklı gönderim çeşitlerinden biri
 * olmasına bağlı olarak alt sınıflar tarafından extend edilir.
 * 
 * @author BiP AI
 * 
 */
public class TesResponse {
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
