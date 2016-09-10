package com.turkcell.bipai.helloworld.model.tes.data;

/**
 * 
 * İçeriğin tip bilgisini tutan enum'dur. Hangi kodun hangi tip bilgisine karşıık verdiği gösterilir.
 * @author BiP AI
 *
 * @see <a href="http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/">Tip bilgileri</a>
 */

public enum Ctype {

	Text(0), /*HTML(1),*/ Image(2), Audio(3), Video(4), Sticker(5), Caps(6), Location(7), RMM(8);


	private final Integer code;

	
	private Ctype(Integer code) {
		this.code = code;
	}

	
	public static Ctype fromCode(Integer code) {
		for (Ctype ctype : Ctype.values()) {
			if (ctype.code.equals(code)) {	
				return ctype;	
			}
		}
		return null;
	}
}
