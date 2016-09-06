package com.turkcell.bipai.helloworld.model;


/**
 * 
 * TES servisine gönderilecek mesajın bilgilerini tutan model sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */

public class Content {

	/**
	 * Mesajın tip bilgisidir. 
	 * Tip bilgileri için http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/
	 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile</a>
	 */
	private int		type;
	// Gönderilecek mesajın içeriği
	private String	message;
	
	public Content() {
		super();
	}

	public Content(int type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
