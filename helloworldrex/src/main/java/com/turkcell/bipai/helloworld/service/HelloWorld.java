package com.turkcell.bipai.helloworld.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.bipai.helloworld.command.Command;
import com.turkcell.bipai.helloworld.command.HelpCommand;
import com.turkcell.bipai.helloworld.model.BiPRexInput;
import com.turkcell.bipai.helloworld.model.BiPRexMessage;
import com.turkcell.bipai.helloworld.model.BiPRexOutput;
import com.turkcell.bipai.helloworld.model.Composition;
import com.turkcell.bipai.helloworld.model.Type;

/**
 * Bir rest servisidir. 
 * REX API'yi kullanarak "sender" parametresi ile belirtilen kullanıcıya "Merhaba dünya!" metnini gönderir. 
 * @author BiP AI
 *
 */
@RestController
public class HelloWorld {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

	/**
	 * 
	 * Talep (request) gövdesinden JSON formatında sender, type, content gibi bilgileri alarak content tipine göre cevap (response) oluşturur 
	 * ve kullanıcıya gönderilmesini sağlayan {@link #createOutput(BiPRexInput, String)} metodunu çağırır. 
	 * @param bipRexInput JSON formatında giriş
	 * 
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.POST, produces = "application/json")
	public BiPRexOutput hello(@RequestBody BiPRexInput bipRexInput) {
				
		String			sender			=	bipRexInput.getSender();
		Integer			serviceId		=	bipRexInput.getServiceId();
		String			transactionId	=	bipRexInput.getTransactionId();
		String			msgId			=	bipRexInput.getMsgId();
		String			receiveDate		=	bipRexInput.getReceiveDate();
		Composition		composition		=	bipRexInput.getComposition();
		Command			command			=	null;
		BiPRexMessage	bipRexMessage	=	composition.getList().get(0);
		String			message			=	bipRexMessage.getMessage();
		BiPRexOutput	output			=	null;
		
		logger.info("sender: " + sender + " - transactionId: " + transactionId + " - msgId: " + msgId + " - receiveDate: " + receiveDate + " - serviceId: " + serviceId);
			
		Type	type		=	Type.fromCode(bipRexMessage.getType());
		switch (type) {
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
			case Sticker:
				logger.info("Sticker input message");
				break;
			
			case Text:
				logger.info("Text input message");
				if ("yardım".equals(message)) {
					command	=	new HelpCommand();
					output	=	createOutput(bipRexInput, command.handle(sender, null));
				} else {
					output	=	createOutput(bipRexInput, "Merhaba dünya!");
				}
				break;
				
			case Video:
				logger.info("Video input message");
				break;
			default:
				break;
		}
		
		return output;
	}

	/**
	 * REX servisine cevap olarak dönülecek BiPRexOutput tipindeki nesneyi oluşturur. Bu nesne REX'e cevap olarak görünür. 
	 * 
	 * @param input Bu porperty'den gelen mesajın transaction id'si alınır ve bu id ile output oluşturulur. 
	 * @param text Dönülecek mesajın içeriğidir. 
	 * 
	 * @see <a href="http://www.bip.ai/documentations/rex-api/">http://www.bip.ai/documentations/rex-api</a>
	 */
	private BiPRexOutput createOutput(BiPRexInput input, String text) {
		BiPRexOutput response = new BiPRexOutput(input.getTransactionId());
		
		Composition composition = new Composition();
		List<BiPRexMessage> responseList = new ArrayList<BiPRexMessage>();
		composition.setList(responseList);
		
		BiPRexMessage saacMessage = new BiPRexMessage();
		saacMessage.setMessage(text);
		saacMessage.setType(0);
		
		responseList.add(saacMessage);
		response.setComposition(composition);
		return response;
	}
	
}
