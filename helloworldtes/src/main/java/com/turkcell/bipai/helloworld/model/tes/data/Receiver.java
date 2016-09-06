package com.turkcell.bipai.helloworld.model;

/**
 * 
 * Alıcı bilgilerini tutan sınıftır. 
 * Alıcı tipi bilgileri için www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile
 * 
 * @author BiP AI
 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile</a>
 */

public class Receiver {

	private int		type;
	private String	address;
	
	
	public Receiver() {
		super();
	}
	
	
	public Receiver(int type, String address) {
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
	
	
	
}
