package com.turkcell.bipai.helloworld.model;


/**
 * 
 * TES servisi üzerinden mesaj göndermek için gereken bilgileri tutan talep (request) sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class BiPTesPushMessageRequest {

	private String		txnid;
	// Mesaj alıcı listesi
	private Receiver[]	receivers;
	// Mesaj içeriği
	private	Content		content;
	
	public String getTxnid() {
		return txnid;
	}
	
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	
	public Content getContent() {
		return content;
	}
	
	public void setContent(Content content) {
		this.content = content;
	}

	public Receiver[] getReceivers() {
		return receivers;
	}

	public void setReceivers(Receiver[] receivers) {
		this.receivers = receivers;
	}
	
	
}
