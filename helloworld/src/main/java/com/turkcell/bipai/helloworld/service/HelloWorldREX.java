package com.turkcell.bipai.helloworld.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.bipai.helloworld.api.rex.model.Composition;
import com.turkcell.bipai.helloworld.api.rex.model.Content;
import com.turkcell.bipai.helloworld.api.rex.model.RexInput;
import com.turkcell.bipai.helloworld.api.rex.model.Type;
import com.turkcell.bipai.helloworld.api.rex.request.RexRequest;
import com.turkcell.bipai.helloworld.command.Command;
import com.turkcell.bipai.helloworld.command.HelpCommand;


/**
 * Bir rest servisidir. 
 * REX API'yi kullanarak "sender" parametresi ile belirtilen kullanıcıya "Merhaba dünya!" metnini gönderir. 
 * @author BiP AI
 *
 */
@RestController
public class HelloWorldREX {


	private static final Logger logger = Logger.getLogger(HelloWorldREX.class.getName());

	/**
	 * 
	 * Talep (request) gövdesinden JSON formatında sender, type, content gibi bilgileri alarak content tipine göre cevap (response) oluşturur 
	 * ve kullanıcıya gönderilmesini sağlayan {@link #createOutput(BiPRexInput, String)} metodunu çağırır. 
	 * @param bipRexInput JSON formatında giriş
	 * 
	 */
	@RequestMapping(value = "/helloREX", method = RequestMethod.POST, produces = "application/json")	
	public RexRequest hello(@RequestBody RexInput bipRexInput) {
		
		String			keyword			=	bipRexInput.getKeyword();
		String			sender			=	bipRexInput.getSender();
		String			msgId			=	bipRexInput.getMsgId();
		String			transactionId	=	bipRexInput.getTransactionId();
		Integer			serviceId		=	bipRexInput.getServiceId();
		String			receiveDate		=	bipRexInput.getReceiveDate();
		Composition		composition		=	bipRexInput.getComposition();

		Content			bipRexMessage	=	composition.getList().get(0);
		RexRequest		output			=	null;
		Command			command			=	null;
		

	
		logger.info("REX API'den takipçinin gönderdiği mesaj alınıyor...");
		logger.info("İSTEK = keyword: " + keyword + "sender: " + sender + " - transactionId: " + transactionId + " - msgId: " + msgId + " - receiveDate: " + receiveDate + " - serviceId: " + serviceId);
		
		
		Type	type		=	Type.fromCode(bipRexMessage.getType());
		
		// Bu örnekte, takipçinin attığı mesaj tipiyle aynı tipte takipçiye dönüş yapılmıştır. Örneğin; takipçi servise görsel mesaj attıysa, takipçiye görsel mesaj
		// dönüşü yap. Kendi sisteminizin nasıl işleyeceğine göre farklı senaryolar kurabilirsiniz.
		
		switch (type) {										
		case Audio:
			logger.info("Takipçiden Audio tipinde mesaj alındı. Takipçiye Audio tipinde mesaj atılıyor...");
			break;
		case Caps:
			logger.info("Takipçiden Caps tipinde mesaj alındı. Takipçiye Caps tipinde mesaj atılıyor...");
			break;
		case Image:
			logger.info("Takipçiden Image tipinde mesaj alındı. Takipçiye Image tipinde mesaj atılıyor...");
			break;
		case Location:
			logger.info("Takipçiden Location tipinde mesaj alındı. Takipçiye Location tipinde mesaj atılıyor...");
			break;
		case Sticker:
			logger.info("Takipçiden Sticker tipinde mesaj alındı. Takipçiye Sticker tipinde mesaj atılıyor...");
			break;
		
		case Text:
			logger.info("Takipçiden Text tipinde mesaj alındı. Takipçiye Text tipinde mesaj atılıyor...");
			if ("yardım".equals(keyword)) {		// Takipçi /yardım komutu gönderdiyse, komutu algıla ve hazırladığınız yardım rehberini görüntüle.
				command	=	new HelpCommand();
				output	=	createOutput(bipRexInput, command.handle(sender, null));
			} else if("DOLAR".equals(keyword)){
				
				// REX kullanıcının yazdığı mesajı Bip Ussü'nde tanımladığınız "dolar" keywordune benzeyip benzemediğine göre
				// gelen mesajı web servisinize iletti. Bu komuta karşılık ne işlemler yapacağınıza karar verip kullanıcıya dinamik içerik, örneğin
				// güncel dolar kurunun hesaplanıp dönülmesi giib işlem yapılıp takipçiye dönülür.
				
				logger.info("Dolar kuru dönülüyor.");
				output	=	createOutput(bipRexInput,"USD = 3,05");
				
			} else if("EURO".equals(keyword)) {
				
				// Euro kuru dönülüyor.
				logger.info("Euro kuru dönülüyor.");
				output	=	createOutput(bipRexInput,"EURO = 3,32");
			}
			else {
				logger.info("Eşleşme bulunamadı. Varsayılan mesaj dönülüyor.");
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
	 * REX servisine cevap olarak dönülecek RexRequest tipindeki nesneyi oluşturur. Bu nesne REX'e cevap olarak görünür. 
	 * 
	 * @param input Bu porperty'den gelen mesajın transaction id'si alınır ve bu id ile output oluşturulur. 
	 * @param text Dönülecek mesajın içeriğidir. 
	 * 
	 * @see <a href="http://www.bip.ai/documentations/rex-api/">http://www.bip.ai/documentations/rex-api</a>
	 */
	private RexRequest createOutput(RexInput input, String text) {
		RexRequest response = new RexRequest(input.getTransactionId());
		
		Composition composition 	 = new Composition();
		List<Content> responseList	 = new ArrayList<Content>();
		composition.setList(responseList);
		
		Content saacMessage 		 = new Content();
		saacMessage.setMessage(text);
		saacMessage.setType(0);
		
		responseList.add(saacMessage);
		response.setComposition(composition);
		return response;
	}
}
