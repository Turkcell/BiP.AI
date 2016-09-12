package com.turkcell.bipai.helloworld.api.tes.request;

import java.util.List;

import com.turkcell.bipai.helloworld.api.tes.model.Receiver;

/**
 * 
 * TES servisi üzerinden çok kullanıcıya aynı mesajı/mesajları göndermek için gereken bilgileri tutan talep (request) sınfıdır.
 * Birden fazla kullanıcıya aynı içeriği gönderdiğinden dolayı TesRequest değişkenlerine ek olarak receivers değişkenine ihtiyacı vardır.
 * 
 * @author BiP AI
 * 
 */

public class TesMultiUserSameMessageRequest extends TesRequest {

	private List<Receiver> receivers;

	public List<Receiver> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<Receiver> receivers) {
		this.receivers = receivers;
	}
	
}
