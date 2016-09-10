package com.turkcell.bipai.helloworld.api.tes.model;

/**
 * 
 * Alıcı bilgilerini tutan sınıftır. 
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

	private Integer type;		// Karıştırılmış-Opaque numara kullanılacaksa "0"
								// MSISDN - gerçek numara kullanılacaksa "2"
								// Tüm takipçilere gönderilmek isteniyorsa "1" olmalıdır.
	
	private String	address;	// Alıcının adresi. Type "0" ise adres "CEA1122AKUL1034EUAYYYYAKM1HA0134" formatında, "2" ise "9053XXXXXXXX" formatında
								// "1" ise boş bırakılmalıdır.
	
	public Receiver(Integer type, String address) {
		super();
		this.type = type;
		this.address = address;
	}
	
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
