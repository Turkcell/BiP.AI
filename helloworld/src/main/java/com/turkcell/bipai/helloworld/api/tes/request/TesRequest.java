
package com.turkcell.bipai.helloworld.api.tes.request;

import com.turkcell.bipai.helloworld.api.tes.model.Composition;

/**
 * 
 * TES servisi üzerinden mesaj göndermek için TES servisinin kabul ettiği JSON formatına uygun alanları tutan talep (request) sınıfıdır.
 * Mesajın tek kullanıcı, çok kullanıcı veya çok kulalnıcıya farklı gönderim çeşitlerinden biri
 * olmasına bağlı olarak alt sınıflar tarafından extend edilir.
 * 
 * @author BiP AI
 * 
 */
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
