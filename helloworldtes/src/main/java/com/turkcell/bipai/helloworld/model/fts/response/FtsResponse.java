package com.turkcell.bipai.helloworld.model.fts.response;


/**
 * 
 * FTS servisi upload çağrısı sonrasında gelen cevabın bilgilerini tutan cevap (response) sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class FtsResponse {

	// FTS servisinden gelen her cevabın id'sidir. Her cevaba tekil bir id atanır. 
	private String	txnid;
	// FTS servisinden gelen cevabın sonuç kodudur. 
	private int		status;
	// FTS servisinden gelen ve upload edilen dosyanın oluşan URL'idir. 
	private String	url;
	
	public String getTxnid() {
		return txnid;
	}
	
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	
	public int getResultcode() {
		return status;
	}
	
	public void setResultcode(int resultcode) {
		this.status = resultcode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
