package com.turkcell.bipai.helloworld.api.rex.model;

/**
 * 
 * Mesajın tip bilgisini tutan enum'dur.
 * Parametre bilgileri için http://www.bip.ai/documentations/rex-api/
 *
 * @author BiP AI
 * 
 * @see <a href="http://www.bip.ai/documentations/rex-api/">http://www.bip.ai/documentations/rex-api</a>
 */

public enum Type {

	Text(0), Image(2), Audio(3), Video(4), Sticker(5), Caps(6), Location(7);

	private final int code;

	private Type(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Type fromCode(int code) {
		for (Type type : Type.values()) {
			if (type.code == code) {
				return type;
			}
		}
		return null;
	}
}
