
package com.turkcell.bipai.helloworld.model.tes.request;

import com.turkcell.bipai.helloworld.model.tes.data.Composition;

/**
 * 
 * TES servisi üzerinden mesaj göndermek için gereken bilgileri tutan talep (request) üst sınıfıdır. 
 * Mesajın tek kullanıcı, çok kullanıcı veya çok kulalnıcıya farklı gönderim çeşitlerinden biri
 * olmasına bağlı olarak alt sınıflar tarafından extend edilir.
 * 
 * @author BiP AI
 * 
 */
public class TesRequest {
	
	private String			txnid;
	private Composition 	composition;
	private Integer			expire;
	private String			fileUrl;
	private String			fileType;
	
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


	public String getFileUrl() {
		return fileUrl;
	}


	public void setFileUrl(String uploadUrl) {
		this.fileUrl = uploadUrl;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
