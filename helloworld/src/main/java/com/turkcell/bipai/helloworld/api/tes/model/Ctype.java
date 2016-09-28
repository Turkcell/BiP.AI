package com.turkcell.bipai.helloworld.api.tes.model;

/**
 * 
 * Mesajın tip bilgisini tutan enum'dur.
 * TES üzerinden mesaj alırken, TES mesaj tipini JSON ctype içerisinde T, I, A gibi değerlerle gönderir. 
 * TES üzerinden mesaj gönderirken ise;
	<pre>
	T için -> 0
	I için -> 2
	A için -> 3
	V için -> 4
	S için -> 5
	C için -> 6
	L için -> 7
	R için -> 8
	</pre>
   değerleri, gönderilecek talep (request) nesnesinin composition->list->type alanına setlenir. Çünkü TES mesaj gönderim isteğini
   karaktere göre değil integer değerine göre kabul etmektedir. TES'ten alınan karakterler TES'e gönderilirken Integer karşılıklarına dönüştürülerek gönderilir.
	
 * @author BiP AI
 *
 * @see <a href="http://www.bip.ai/documentations/birden-fazla-kullaniciya-ayni-mesaj-gonderimi/">http://www.bip.ai/documentations/birden-fazla-kullaniciya-ayni-mesaj-gonderimi/</a>

 */

public enum Ctype {

	Text("T", 0), Image("I", 2), Audio("A", 3), Video("V", 4), Sticker("S", 5), Caps("C", 6), Location("L", 7), RMM("R", 8);

	private final String character;
	private final Integer code;

	
	private Ctype(String abbr, Integer code) {
		this.character = abbr;
		this.code = code;
	}

	
	
	public static Ctype fromCharToText(String character) {
		for (Ctype ctype : Ctype.values()) {
			if (ctype.character.equals(character)) {	
				return ctype;	
			}
		}
		return null;
	}
	
	public static Integer fromCharToInteger(String character) {
		for (Ctype ctype : Ctype.values()) {
			if (ctype.character.equals(character)) {	
				return ctype.code;	
			}
		}
		return null;
	}
	
}