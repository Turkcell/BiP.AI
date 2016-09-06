package com.turkcell.bipai.helloworld.model.tes.request;

import java.util.List;

import com.turkcell.bipai.helloworld.model.tes.data.ReceiverContent;

/**
 * 
 * TES servisi üzerinden çok kullanıcıya farklı mesaj/mesajları göndermek için gereken bilgileri tutan talep (request) sınfıdır.
 * Birden fazla kullanıcıya farklı içeriği gönderdiğinden dolayı TesRequest değişkenlerine ek olarak
 * receivercontentlist değişkenine ihtiyacı vardır.
 * 
 * @author BiP AI
 * 
 */

public class TesMultiUserRequest extends TesRequest {
	
	private List<ReceiverContent> receivercontentlist;

	public List<ReceiverContent> getReceivercontentlist() {
		return receivercontentlist;
	}

	public void setReceivercontentlist(List<ReceiverContent> receivercontentlist) {
		this.receivercontentlist = receivercontentlist;
	}
	
}
