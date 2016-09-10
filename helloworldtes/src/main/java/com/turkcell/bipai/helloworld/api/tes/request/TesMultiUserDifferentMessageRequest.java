package com.turkcell.bipai.helloworld.api.tes.request;

import java.util.List;

import com.turkcell.bipai.helloworld.api.tes.model.ReceiverContent;

/**
 * 
 * TES servisi üzerinden çok kullanıcıya farklı mesaj/mesajları göndermek için gereken bilgileri tutan talep (request) sınfıdır.
 * Birden fazla kullanıcıya farklı içeriği gönderdiğinden dolayı TesRequest değişkenlerine ek olarak
 * receivercontentlist değişkenine ihtiyacı vardır.
 * 
 * @author BiP AI
 * 
 */

public class TesMultiUserDifferentMessageRequest extends TesRequest {
	
	private List<ReceiverContent> receivercontentlist;

	public List<ReceiverContent> getReceivercontentlist() {
		return receivercontentlist;
	}

	public void setReceivercontentlist(List<ReceiverContent> receivercontentlist) {
		this.receivercontentlist = receivercontentlist;
	}
	
}
