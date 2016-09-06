package com.turkcell.bipai.helloworld.model.tes.request;

import com.turkcell.bipai.helloworld.model.tes.data.Receiver;

/**
 * 
 * TES servisi üzerinden tek kullanıcıya mesaj göndermek için gereken bilgileri tutan talep (request) sınfıdır.
 * Tek kullanıcıya içerik gönderidğinden dolayı TesRequest değişkenlerine ek olarak receiver değişkenine ihtiyacı vardır.
 * 
 * @author BiP AI
 * 
 */

public class TesSingleUserRequest extends TesRequest {

	private Receiver receiver;

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	
}
