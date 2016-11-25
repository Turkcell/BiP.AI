package com.turkcell.bipai.merchantsimulator.api.tes.request;

import com.turkcell.bipai.merchantsimulator.api.tes.model.Composition;

public class TesRequest { 
	
	private String			txnid;			// Mesaja ait tekil id
	private Composition 	composition;	// Her bir mesajın Content olarak tutulduğu Listeyi saklayan model
	private Integer			expire;			// Mesajın takipçide görünme süresi
	
	public String getTxnid() {
		return txnid;
	}
	
	
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	
	
	public Integer getExpire() {
		return expire;
	}
	
	
	public void setExpire(Integer expire) {
		this.expire = expire;
	}
	
	
	public Composition getComposition() {
		return composition;
	}
	
	
	public void setComposition(Composition composition) {
		this.composition = composition;
	}

}
