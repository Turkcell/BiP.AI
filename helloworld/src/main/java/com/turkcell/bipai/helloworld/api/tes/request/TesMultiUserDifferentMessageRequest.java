package com.turkcell.bipai.helloworld.api.tes.request;

import java.util.List;

import com.turkcell.bipai.helloworld.api.tes.model.ReceiverContent;

/**
 * 
 * TES servisi üzerinden çok kullanıcıya farklı mesaj/mesajları göndermek için gereken bilgileri tutan talep (request) sınfıdır.
 * 
 * @author BiP AI
 * 
 */

public class TesMultiUserDifferentMessageRequest {
	
	private String				  	txnid;
	private List<ReceiverContent> 	receivercontentlist;
	
	public List<ReceiverContent> getReceivercontentlist() {
		return receivercontentlist;
	}

	public void setReceivercontentlist(List<ReceiverContent> receivercontentlist) {
		this.receivercontentlist = receivercontentlist;
	}

	public String getTxnid() {
		return txnid;
	}

	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	
}
