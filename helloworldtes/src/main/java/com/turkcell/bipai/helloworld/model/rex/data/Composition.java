
package com.turkcell.bipai.helloworld.model.rex.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Mesaj gönderimi için kullanılır.
 * 
 * @author BIP AI
 *
 */
public class Composition {

	private List<Content> list = new ArrayList<Content>();

	public List<Content> getList() {
		return list;
	}

	public void setList(List<Content> list) {
		this.list = list;
	}

}
