package com.turkcell.bipai.helloworld.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.bipai.helloworld.api.tes.model.Composition;
import com.turkcell.bipai.helloworld.api.tes.model.Content;
import com.turkcell.bipai.helloworld.api.tes.model.Receiver;
import com.turkcell.bipai.helloworld.api.tes.model.ReceiverContent;
import com.turkcell.bipai.helloworld.api.tes.request.TesMultiUserDifferentMessageRequest;
import com.turkcell.bipai.helloworld.api.tes.request.TesMultiUserSameMessageRequest;
import com.turkcell.bipai.helloworld.util.AppConstant;

/**
 * TES apisi üzedinden birden fazla kullanıcıya mesaj gönderilmek istenirse kullanılması gereken sınıfları gösteren örnekler.
 *
 */
@RestController
public class HelloWorldMultiUsers {


	/**
	 *  Çok kullanıcıya aynı ve çok kullanıcıya farklı mesaj gönderimi tek bir örneke gösterildiği web servis. Örneği çalıştırmak için
	 * /helloMultiUsers linkine json tipinde boş bir POST isteği yapmak yeterlidir.
	 */
	@RequestMapping(value="/helloMultiUsers", method=RequestMethod.POST, produces="application/json") 
	public void hello(){
		
		// Her takipçiye farklı mesaj gönderileceğinden bir Map içerisinde takipçi numarası ve gönderilecek mesaj setlenip fonksiyona parametre olarak gönderiliyor.
		Map<String, String> usersAndMessage = new HashMap<String, String>();
		usersAndMessage.put("05a6d402f40383e4c016302e2dca75a2", "Merhaba takipçi 1");
		usersAndMessage.put("d8077d46ae538e1866f224a843ae8088", "Merhaba takipçi 2");
		respondWithTextMultiUserDifferentMessage(usersAndMessage);
		
		// Her takipçiye aynı mesaj gönderileceğinden bir ArrayList içerisine takipçi numaraları setleniyor. Gönderilecek mesaj ise bir String içerisinde tutulup
		// paramatreler ilgili fonksiyona iletiliyor.
		List<String> followers = new ArrayList<String>();
		followers.add("05a6d402f40383e4c016302e2dca75a2");
		followers.add("d8077d46ae538e1866f224a843ae8088");
		String message	=	"Herkese Merhaba";
		respondWithTextMultiUserSameMessage(followers, message);
	}
	
	
	/**
	 * Çok takipçiye aynı mesajın gönderilmesi. İstek TesMultiUserMaseMessageRequest sınıfı kullanılarak yapılır.
	 * Takipçiler Receiver sınıfında oluşturulur ve receivers adlı bir listeye eklenir. 
	 * @param users mesaj gönderilecek takipçilerin numarasını tutan liste
	 * @param message takipçilere gönderilecek mesaj
	 */
	public void respondWithTextMultiUserSameMessage(List<String> followers, String message) {
		
		TesMultiUserSameMessageRequest request	=	new TesMultiUserSameMessageRequest();				// Çok kişiye aynı gönderilecek JSON'u tutan model.
		Service service 						= 	new Service();										// Mesajların gönderildiği servis sınıfı.
			
		List<Receiver> receivers				=	new ArrayList<Receiver>();							// Mesaj alıcıları Receiver modelini tutan receivers listesine eklenir.
		
		for (String follower : followers ) {			
			receivers.add(new Receiver(AppConstant.USER_NUMBER_TYPE, follower));	//  List içerisinden takipçilerin numarası alındı ve her biri için yeni bir Receiver sınıfı oluşturulup
																					//  receivers listesine eklendi.
		}	

		Composition   composition				=	new Composition();			
		List<Content> contents 					=	new ArrayList<Content>();	
		Content		  content					=	new Content();
			
		content.setType(0);															// gönderilecek mesaj tipi girildi. Bu örnekte text mesaj tipi gönderildi.						
		content.setMessage(message);												// Receiver sınıfında oluşturulan takipçiye yollanacak mesaj eklendi.
		contents.add(content);														// content'e eklendi									
			
		composition.setList(contents);									
		
		request.setTxnid(UUID.randomUUID().toString());					
		request.setReceivers(receivers);								
		request.setComposition(composition);							
		
		service.send(request);										
	}
	
	/**
	 * Çok takipçiye farklı mesajın gönderilmesi. İstek TesMultiUserDifferentMessageRequest sınıfı kullanılarak yapılır.
	 * Takipçiler ReceiverContent sınıfında oluşturulur. Her takipçi ve mesaj bilgisi ReceiverContent sınıfında tutulur.
	 * Oluşturulan sınıflar receivercontentlist adlı bir listeye eklenir. 
	 * @param usersAndMessage mesajı alacak takipçinin numarası ve gönderilecek mesajın olduğu Map
	 */
	public void respondWithTextMultiUserDifferentMessage(Map<String, String> usersAndMessage) {
		
		TesMultiUserDifferentMessageRequest request	=	new TesMultiUserDifferentMessageRequest();		// Çok kişiye farklı gönderilecek JSON'u tutan model.
		Service service = new Service();																// Mesajların gönderildiği servis sınıfı.
			
		List<ReceiverContent> receivercontentlist		=	new ArrayList<ReceiverContent>();			// Mesaj alıcıları ve her alıcıya gönderilecek mesaj ReceiverContent sınıfında tutulur.
																										// Oluşturulan ReceiverContent'ler receivercontentlist listesine kaydedilir.
		
		for (Map.Entry<String, String> user : usersAndMessage.entrySet()) {
			ReceiverContent follower 		= 	new ReceiverContent();				
			
			follower.setReceiver(new Receiver(AppConstant.USER_NUMBER_TYPE, user.getKey()));			//  Map içerisinden takipçinin numarası alındı ve yeni bir Receiver sınıfı oluşturuldu.
	
			Composition   composition			=	new Composition();			
			List<Content> contents 				=	new ArrayList<Content>();	
			Content		  content				=	new Content();	
			
			content.setType(0);															// gönderilecek mesaj tipi girildi. Bu örnekte text mesaj tipi gönderildi.						
			content.setMessage(user.getValue());										// Receiver sınıfında oluşturulan takipçiye yollanacak mesaj Map içerisinden alındı.		
			contents.add(content);														// contentler list'e eklendi
			composition.setList(contents);												// list, composition'a eklendi
			follower.setComposition(composition);										// composition, receivercontent'e eklendi
			receivercontentlist.add(follower);											// receivercontent, receivercontentlistd'e eklendi.
		}

		request.setTxnid(UUID.randomUUID().toString());	
		request.setReceivercontentlist(receivercontentlist);
		service.send(request);											
	}
}
