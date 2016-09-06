package com.turkcell.bipai.helloworld.model.tes.data;

/**
 * 
 * Alıcı bilgilerini tutan sınıftır. Tek kullanıcıya mesaj gönderilmek isteniyorsa Receiver nesnesinin
 * type değişkeni "0" (Karıştırılmış-Opaque numara) veya "2" (MSISDN - gerçek numara) olmalı, tüm takipçilere gönderilmek 
 * isteniyorsa "1" olmalıdır.
 * Format:
 * 
 <pre>
{
    ...
    "receiver":{
        "type":2, 
        "address":"9053XXXXXXXX"

    }
    ...
}
</pre>
 * @author BiP AI
 * @see <a href="http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/">Alıcı bilgileri</a>
 */

public class Receiver {

	private Integer type;
	private String	address;
	
	public int getType() {
		return type;
	}
	
	
	public void setType(int type) {
		this.type = type;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	
	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Receiver [type=" + type + ", address=" + address + "]";
	}
	
	
}
