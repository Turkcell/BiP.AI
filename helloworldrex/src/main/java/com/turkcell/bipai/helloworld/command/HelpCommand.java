package com.turkcell.bipai.helloworld.command;

import java.util.List;

/**
 * "/yardım" veya "/help" komutunu modellemek için kullanılacak sınıf.
 * 
 * @author BIP AI
 *
 */

public class HelpCommand implements Command {
	
	public static final String NAME		=	"help";
	
	
	@Override
	public String handle(String sender, List<Object> params) {
		return	" HelloWorld Servisi yazılan mesajları karşılar. \n" + 
				" Metin olarak mesaj gönderebilirsiniz.";
	}

	
	@Override
	public String getName() {
		return NAME;
	}
	
}
