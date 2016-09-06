package com.turkcell.bipai.helloworld.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.turkcell.bipai.helloworld.command.Command;
import com.turkcell.bipai.helloworld.command.HelpCommand;
import com.turkcell.bipai.helloworld.model.BiPTesInput;
import com.turkcell.bipai.helloworld.model.BiPTesPushMessageRequest;
import com.turkcell.bipai.helloworld.model.BiPTesPushMessageResponse;
import com.turkcell.bipai.helloworld.model.Content;
import com.turkcell.bipai.helloworld.model.Ctype;
import com.turkcell.bipai.helloworld.model.Receiver;
import com.turkcell.bipai.helloworld.util.BasicAuthRestTemplate;


/**
 * 
 * TES API'yi kullanarak "sender" parametresi ile belirtilen kullanıcıya "Merhaba dünya!" metnini gönderir. 
 * @author BiP AI
 *
 */
@RestController
public class HelloWorld {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
	
	/**
	 * 
	 * Talep (request) gövdesinden JSON formatında sender, type, content gibi bilgileri alarak content tipine göre cevap (response) oluşturur 
	 * ve kullanıcıya gönderilmesini sağlayan {@link #respondText(String, String)} metodunu çağırır. 
	 * @param biPTesInput JSON formatında giriş (Ör: {"type": "2", "sender": "90532XXXXXXX", "ctype": "T", "content": "", "msgid": 12, "sendtime": ""})
	 * 
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.POST, produces = "application/json")
	public void hello(@RequestBody BiPTesInput biPTesInput) {
		
		String	sender		=	biPTesInput.getSender();
		Integer	msgid		=	biPTesInput.getMsgid();
		String	sendtime	=	biPTesInput.getSendtime();
		String	type		=	biPTesInput.getType();
		String	ctypeVal	=	biPTesInput.getCtype();
		String	content		=	biPTesInput.getContent();
		
		Command	command		=	null;
		
		logger.info("sender: " + sender + " - msgid: " + msgid + " - content: " + content + " - type: " + type + " - ctype: " + ctypeVal + " - sendtime: " + sendtime);
		
		Ctype	ctype		=	Ctype.fromCode(ctypeVal);
		switch (ctype) {
			case Audio:
				logger.info("Audio input message");
				break;
			case Caps:
				logger.info("Caps input message");
				break;
			case Image:
				logger.info("Image input message");
				break;
			case Location:
				logger.info("Location input message");
				break;
			case RMM:
				logger.info("RMM input message");
				break;
			case Sticker:
				logger.info("Sticker input message");
				break;
			
			case Text:
				logger.info("Text input message");
				if ("yardım".equals(content)) {
					command	=	new HelpCommand();
					respondText(sender, type, command.handle(sender, null));
				} else {
					respondText(sender, type, "Merhaba dünya!");
				}
				break;
				
			case Video:
				logger.info("Video input message");
				break;
			default:
				break;
		}
	}

	
	/**
	 * TES servisinin sendmsgservlist metodunu kullanarak, sender ile belirtilen kullanıcıya message ile belirtilen mesajı gönderir.
	 * username ve password bilgilerini size ait bilgiler ile değiştirmeniz gerekmektedir. 
	 * @param sender Mesajın gönderileceği kişiyi ifade eden metin
	 * @param type sender tipi 
	 * @param message Mesaj içeriği
	 * 
	 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile</a>
	 */
	private void respondText(String sender, String type, String message) {
		
		RestTemplate				restTemplate	=	new BasicAuthRestTemplate("user", "password");
		BiPTesPushMessageRequest	request			=	new BiPTesPushMessageRequest();
		
		request.setTxnid(UUID.randomUUID().toString());
		request.setContent (new Content (0, message));
		request.setReceivers(new Receiver[]{new Receiver(Integer.parseInt(type), sender)});
		
		logger.info("REQUEST_JSON: " + new Gson().toJson(request));
		
		BiPTesPushMessageResponse	response		=	restTemplate.postForObject("https://prptims.turkcell.com.tr/tes/rest/spi/sendmsgservlist", request, BiPTesPushMessageResponse.class);
		
		logger.info("Result Code: " + response.getResultcode());
	}
	
}
