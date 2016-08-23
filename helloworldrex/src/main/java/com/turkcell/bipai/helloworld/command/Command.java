package com.turkcell.bipai.helloworld.command;

import java.util.List;

/**
 * "/" ile başlayan komutları modellemek için kullanılacak interface. Ör: /yardım
 * 
 * @author BIP AI
 *
 */
public interface Command {

	public String handle(String sender, List<Object> params);
	
	public String getName();

}
