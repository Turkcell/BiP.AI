package com.turkcell.bipai.helloworld.model;

/**
 * 
 * Mesajın tip bilgisini tutan enum'dur.
 * Tip bilgileri için www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile
 * @author BiP AI
 *
 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile</a>
 */

public enum Ctype {

	Text("T"), Image("I"), Audio("A"), Video("V"), Caps("C"), Sticker("S"), Location("L"), RMM("R");

	private final String code;

	
	private Ctype(String code) {
		this.code = code;
	}

	
	public String toString() {
		return code;
	}
	
	
	public static Ctype fromCode(String code) {
		for (Ctype ctype : Ctype.values()) {
			if (ctype.code.equals(code)) {	
				return ctype;	
			}
		}
		return null;
	}
}
