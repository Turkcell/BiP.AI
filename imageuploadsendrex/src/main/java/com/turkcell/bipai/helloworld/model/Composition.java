
package com.turkcell.bipai.helloworld.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Mesaj gönderimi için kullanılır.
 * 
 * @author BIP AI
 *
 */
public class Composition {

	private List<BiPRexMessage> list = new ArrayList<BiPRexMessage>();

	public List<BiPRexMessage> getList() {
		return list;
	}

	public void setList(List<BiPRexMessage> list) {
		this.list = list;
	}

}
