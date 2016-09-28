package com.turkcell.bipai.helloworld.service;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.bipai.helloworld.api.tes.model.Composition;
import com.turkcell.bipai.helloworld.api.tes.model.Content;
import com.turkcell.bipai.helloworld.api.tes.model.Ctype;
import com.turkcell.bipai.helloworld.api.tes.model.Option;
import com.turkcell.bipai.helloworld.api.tes.model.Receiver;
import com.turkcell.bipai.helloworld.api.tes.model.RichMedia;
import com.turkcell.bipai.helloworld.api.tes.model.TesInput;
import com.turkcell.bipai.helloworld.api.tes.request.TesMultiUserDifferentMessageRequest;
import com.turkcell.bipai.helloworld.api.tes.request.TesMultiUserSameMessageRequest;
import com.turkcell.bipai.helloworld.api.tes.request.TesSingleUserRequest;
import com.turkcell.bipai.helloworld.command.Command;
import com.turkcell.bipai.helloworld.command.HelpCommand;
import com.turkcell.bipai.helloworld.util.AppConstant;
import com.turkcell.bipai.helloworld.util.Upload;

/**
 * 
 * TES API'yi kullanarak takipçiden gelen mesajın alınmasını ve daha sonra 1 veya daha fazla mesajın 1 kullanıcıya veya tüm takipçilere {@link #tekKisiye(TesSingleUserRequest)},
 * 1'den fazla kullanıcıya aynı {@link #cokKisiyeAyni(TesMultiUserSameMessageRequest)}, 1'den fazla kullanıcıya farklı farklı olarak {@link #cokKisiyeFarkli(TesMultiUserDifferentMessageRequest)}
 * gönderilmesini sağlayan örnekler.
 * @author BiP AI
 *
 */
@RestController
public class HelloWorldTES {
	
	private static final Logger logger = Logger.getLogger(HelloWorldTES.class.getName());

	/**
	 * 
	 * TES'in takipçiden alıp ilettiği mesajı TesInput gövdesinden JSON formatında alarak TES'e HTTP 200 döner. Gelen mesaj tipine göre cevap (response) oluşturur 
	 * ve kullanıcıya gönderilmesini sağlayan ilgili metodları çağırır. 
	 * @param biPTesInput JSON formatında giriş Ör:
	 * <pre>
	 * {
		   "sender":"05a6d402f40383e4c016302e2dca75a2",
		   "msgid":9321,
		   "sendtime":"12.10.2015 08:00:00.123 +0300",
		   "type":"M",
		   "ctype":"I",
		   "content":"merhaba"
		}
	 * </pre>
	 * 
	 */
	@RequestMapping(value = "/helloTES", method = RequestMethod.POST, produces = "application/json")
	public BodyBuilder hello(@RequestBody TesInput biPTesInput) {
		
		// Gelen input, mesaj da olsa bildirim de olsa gelen JSON içerisinde bulunacak kısımlar
		String	sender		=	biPTesInput.getSender();
		Integer	msgid		=	biPTesInput.getMsgid();
		String	sendtime	=	biPTesInput.getSendtime();
		String	type		=	biPTesInput.getType();		// Takipçiden gelen mesaj web servisinize iletildiyse "M", takip etme/takibi
															// bırakma gibi bir bildirim geldiyse "E" değeri olarak gelir.
		
		// Gelen input, takipçinin gönderdiği bir mesaj ise gelen JSON içerisinde bulunacak kısımlar
		String	ctype		=	biPTesInput.getCtype();
		String	content		=	biPTesInput.getContent();
		Integer pollid		=	biPTesInput.getPollid();	// ctype "R" yani anket ise bir değer gelecektir
		Integer optionid	=	biPTesInput.getOptionid();	// ctype "R" yani anket ise bir değer gelecektir
		
		// Gelen input, kullanıcının abone olması veya aboneliği bırakması ile ilgili bir bildirimse gelen JSON içerisinde bulunacak kısımlar
		String event		=	biPTesInput.getEvent();
		
		// Takipçi sistemimizde tanımlı komutları kullandıysa, ilgili sınıflarla cevap verilir
		Command	command		=	null;
		
		// type "M" değerine eşitse TES, takipçinin gönderidği mesaja biPTesInput içerisinde gönderdi.
		
		if("M".equals(type)) {
			logger.info("TES API'den takipçinin gönderdiği mesaj alınıyor...");
			logger.info("İSTEK : " +"sender: " + sender + " - msgid: " + msgid + " - sendtime: " + sendtime + 
						" - type: " + type + " - ctype: " + ctype + " - content: " + content + " - pollid: " + pollid + " - optionid: " + optionid);
			
			Ctype	ctypeText		=	Ctype.fromCharToText(ctype);	// Gelen JSON isteğinde mesajın tipi A, C, I gibi karakterlerle temsil edilir.
																		// Bu karakterlerin hangi mesaj tipini temsil ettiği Ctype enum'undan öğreniir.
			
			// Bu örnekte, takipçinin attığı mesaj tipiyle aynı tipte takipçiye dönüş yapılmıştır. Örneğin; takipçi servise görsel mesaj attıysa, takipçiye görsel mesaj
			// dönüşü yap. Kendi sisteminizin nasıl işleyeceğine göre farklı senaryolar kurabilirsiniz.
			
			switch (ctypeText) {										
				case Audio:
					logger.info("Takipçiden Audio tipinde mesaj alındı. Takipçiye Audio tipinde mesaj atılıyor...");
					break;
				case Caps:
					logger.info("Takipçiden Caps tipinde mesaj alındı. Takipçiye Caps tipinde mesaj atılıyor...");
					break;
				case Image:
					logger.info("Takipçiden Image tipinde mesaj alındı. Takipçiye Image tipinde mesaj atılıyor...");					
					try {
						respondWithImage(sender, ctype, content);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
					break;
				case Location:
					logger.info("Takipçiden Location tipinde mesaj alındı. Takipçiye Location tipinde mesaj atılıyor...");
					break;
				case Sticker:
					logger.info("Takipçiden Sticker tipinde mesaj alındı. Takipçiye Sticker tipinde mesaj atılıyor...");
					break;
				case Text:
					logger.info("Takipçiden Text tipinde mesaj alındı.");
					
					// Takipçi /yardım komutu gönderdiyse, komutu algıla ve hazırladığınız yardım rehberini görüntüle.
					if ("/yardım".equals(content)) {
						
						command	=	new HelpCommand();
						respondWithText(sender, ctype, command.handle(sender, null));
						
					} else if(content.toLowerCase().contains("rmm")) {
						ctype = "R";
						logger.info("Takipçiye RMM tipinde mesaj atılıyor...");
						
						if(content.toLowerCase().contains("tekil")) {
							logger.info("Tekil RMM gönderiliyor...");
							try {
								respondWithSingleRMM(sender, ctype, "http://bip.ai/wp-content/themes/bip-developers/assets/images/logo-cogs.png");
							} catch (IOException e) {
								e.printStackTrace();
							} catch (URISyntaxException e) {
								e.printStackTrace();
							}
							break;
						}
						else if(content.toLowerCase().contains("çoğul")) {
							logger.info("Çoklu RMM gönderiliyor...");
							List<String> photos		=	new ArrayList<String>();
							photos.add("http://bip.ai/wp-content/themes/bip-developers/assets/images/logo-cogs.png");
							photos.add("https://testtims.turkcell.com.tr/scontent/p2p/28032016/11/P677c015930d282d875be6c13bd917b14dd30def44b817196f06d5fac5bbdd7680.jpg");
							photos.add("http://www.bip.ai/wp-content/uploads/2016/05/slider1-254x300.png");
								try {
									respondWithMultiRMM(sender, ctype, photos);
								} catch (IOException e) {
									e.printStackTrace();
								} catch (URISyntaxException e) {
									e.printStackTrace();
								}
								break;
						}
						else if(content.toLowerCase().contains("anket")) {
							try {
								respondWithPollRMM(sender, ctype, "http://bip.ai/wp-content/themes/bip-developers/assets/images/logo-cogs.png");
							} catch (IOException e) {
								e.printStackTrace();
							} catch (URISyntaxException e) {
								e.printStackTrace();
							}
							break;
						}
					}

					else {
						logger.info("Takipçiye Text tipinde mesaj atılıyor..");
						respondWithText(sender, ctype, "Merhaba! TES API'nin yeteneklerinden örnekler gösterildiği servisimize hoşgeldiniz. "
								+ " Örnek servisimizde takipçiden mesaj alıp; takipçiye metin, fotoğraf ve 3 farklı rmm mesajlarının (tekil, çoğul, anket) TES API kullanılarak"
								+ " gönderilmesi işlenmiştir. TES API'nin daha fazla yeteneğini http://www.bip.ai/documentations/tes-api/ adresinden keşfedebilirsiniz.");
					}
					break;
					
				case Video:
					logger.info("Takipçiden Video tipinde mesaj alındı. Takipçiye Video tipinde mesaj atılıyor...");
					break;
				default:
					break;
			}
		}
		// type "E" değerine eşitse TES, abonelik/abonelikten çıkış bildirimini biPTesInput içerisinde gönderdi.
		else if("E".equals(type)) {
			logger.info("TES API'den bildirim alınıyor...");
			logger.info("CEVAP: " + "sender: " + sender + " - msgid: " + msgid + " - sendtime: " + sendtime + " - type: " + type + " - event: " + event);
			if(event.equals("U"))
				logger.info("Bir takipçi kaybettiniz.");
			if(event.equals("S"))
				logger.info("Yeni bir takipçiniz var.");
		}
		return ResponseEntity.ok();		// Mesaj veya bildirim alındıktan sonra TES'e HTTP 200 dönülmelidir.
	}
	
	
	
	/**
	 * 
	 * Web servisinize mesaj gönderen takipçinin "sender" bilgisini kullanarak takipçiye metin mesajı gönderir. TES API'sinin tek takipçiye mesaj gönderirken 
	 * kabul ettiği mesaj formatı olan SingleUserRequest modeline gönderilecek bilgiler setlenir.
	 * @param sender	takipçinin adres bilgisi (telefon numarası - varsayılan olarak karıştırılmış(opaque) numara)
	 * @param type		takipçinin web servisinize gönderdiği mesaj tipi
	 * @param message	takipçiye gönderilecek mesaj
	 */
	public void respondWithText(String sender, String type, String message) {
		
		/*
		 * tekKisiye URI yına gelen içerik respond fonksiyonuna iletilirek TES API'sinin sendmsgserv fonksiyonu çağırılır.
		 * Burada gelen JSON değerlerinin setter'ları çağırılarak özelleştirilebilir, filtrelemeler koyulabilir,
		 * değiştirilebilir. 
		 *
		 */
		
		TesSingleUserRequest request	=	new TesSingleUserRequest();				// Tek kişiye gönderilecek JSON'u tutan model.
		Service service = new Service();											// Mesajların gönderildiği servis sınıfı.
			
		Composition   composition		=	new Composition();						// Her mesaj listesi composition modelinin içerisinde tutulur.
		List<Content> contents 			=	new ArrayList<Content>();				// Composition listesinin içerisinde Content tipinde mesaj listesi oluşturulur.
		Content		  content			=	new Content();							// listeye eklenecek her mesaj Content modelinde tutulur.
		
		Integer ctype = Ctype.fromCharToInteger(type);		// gelen A, I, T gibi mesaj tipini temsil eden karakterlerin integer karşılığı alınır.

		content.setType(ctype);								// mesajın tipi integer olarak Content'e set edilir.
		content.setMessage(message);						// mesaj Content'e set edilir.
		contents.add(content);								// oluşturulan content, contents listesine set edilir.
		
		composition.setList(contents);						// oluşturulan contents listesi, composition'a set edilir.
		
		request.setTxnid(UUID.randomUUID().toString());								// Tekil bir Txn id oluşturulur, request modeline eklenir.	
		request.setReceiver(new Receiver(AppConstant.USER_NUMBER_TYPE, sender));	// Mesaj alıcısı, tipiyle birlikte request modeline eklenir.
		request.setComposition(composition);										// Gönderilecek olan mesaj bilgilerini tutan composition, request modeline eklenir.
		
		service.send(request);								// mesaj gönderilir.
	}
	
	/**
	 * 
	 * Web servisinize mesaj gönderen takipçinin "sender" bilgisini kullanarak takipçiye fotoğraf gönderir. TES API'sinin tek takipçiye mesaj gönderirken 
	 * kabul ettiği mesaj formatı olan TesSingleUserRequest modeline gönderilecek bilgiler setlenir. Gönderilecek fotoğraf önce FTS API kullanılarak
	 * Turkcell serverlarına yüklenir ve FTS API'den dönülen URL bilgisi, TesSingleUserRequest modelinin mesaj alınana set edilir.
	 * @param sender	takipçinin adres bilgisi (telefon numarası - varsayılan olarak karıştırılmış(opaque) numara)
	 * @param type		takipçinin web servisinize gönderdiği mesaj tipi
	 * @param message	takipçiye gönderilecek fotoğrafın URL'i
	 */
	public void respondWithImage(String sender, String type, String message) throws IOException, URISyntaxException {
		
		TesSingleUserRequest request	=	new TesSingleUserRequest();
		Service service = new Service();
		
		String fileType					=	AppConstant.PHOTO_TYPE;
		Map<String, Object> imageMap	= 	new Upload().uploadToFts(fileType, message); // Dosya FTS'e upload edilir ve url,size bilgileri alınır.

		String ftsImageUrl 				= 	imageMap.get("url").toString(); 			// Görselin URL'i
		int size 						= 	(int)imageMap.get("size"); 					// Görselin boyutu
		
		Composition 	composition 	= 	new Composition();				
		List<Content> 	contents		=	new ArrayList<Content>();		
		Content			content 		=	new Content();			
		
		Integer ctype = Ctype.fromCharToInteger(type);		// gelen A, I, T gibi mesaj tipini temsil eden karakterlerin integer karşılığı alınır.
		
		content.setType(ctype);
		content.setMessage(ftsImageUrl); 					// FTS'ten dönen URL setlenir.
		content.setSize(size); 								// Görselin boyutu
		content.setRatio(1.0f); 							// Görselin ratiosu
		contents.add(content);							
		
		composition.setList(contents); 						

		request.setTxnid(UUID.randomUUID().toString());
		request.setReceiver(new Receiver(AppConstant.USER_NUMBER_TYPE, sender));
		request.setComposition(composition);
		
		service.send(request); 								// değşiklikleri yapılan input, takipçiye send fonksiyonuyla gönderilir.
	}
	
	
	/**
	 * 
	 * Web servisinize mesaj gönderen takipçinin "sender" bilgisini kullanarak takipçiye tekli RMM gönderir. TES API'sinin tek takipçiye mesaj gönderirken 
	 * kabul ettiği mesaj formatı olan TesSingleUserRequest modeline gönderilecek bilgiler setlenir. Gönderilecek fotoğraf önce FTS API kullanılarak
	 * Turkcell serverlarına yüklenir ve FTS API'den dönülen URL bilgisi, TesSingleUserRequest modelinin mesaj alınana set edilir.
	 * @param sender	takipçinin adres bilgisi (telefon numarası - varsayılan olarak karıştırılmış(opaque) numara)
	 * @param type		takipçinin web servisinize gönderdiği mesaj tipi
	 * @param message	takipçiye gönderilecek tekli RMM'deki fotoğrafın URL'i
	 */
	public void respondWithSingleRMM(String sender, String type, String message) throws IOException, URISyntaxException {
		
		TesSingleUserRequest request	=	new TesSingleUserRequest();
		Service service = new Service();
		
		String fileType					=	AppConstant.PHOTO_TYPE;
		Map<String, Object> imageMap	= 	new Upload().uploadToFts(fileType, message);

		String ftsImageUrl 				= 	imageMap.get("url").toString();

		Composition 	composition 	= 	new Composition();
		List<Content> 	contents		=	new ArrayList<Content>();
		Content			content 		=	new Content();
		
		Integer ctype = Ctype.fromCharToInteger(type);	
		
		content.setType(ctype);													
		content.setRichMediaType(AppConstant.SINGLE_RMM); 						
		
		List<RichMedia> richmedialist 	=	new ArrayList<RichMedia>(); 		
		RichMedia		richmedia		=	new RichMedia(); 					
		
		richmedia.setTitle("Bip API");   										
		richmedia.setImage(ftsImageUrl); 									
		richmedia.setRatio(1.0F);	
		richmedia.setDescription("RMM Tekil Medya Örneği");
		richmedia.setUrl("http://www.turkcell.com.tr/bip");
		richmedia.setUrltext("Turkcell Bip hakkında daha fazla bilgi için tıklayınız...");
		
		richmedialist.add(richmedia); 											// anket listeye eklendi.
		
		content.setRichMediaList(richmedialist); 								// liste içeriğe eklinde.
		contents.add(content); 													// içerik listeye eklendi.
		
		composition.setList(contents); 											// liste compostion'a eklendi.
		
		request.setTxnid(UUID.randomUUID().toString());
		request.setReceiver(new Receiver(AppConstant.USER_NUMBER_TYPE, sender));
		request.setComposition(composition);								    // composition gönderilecek requeste eklendi.
		
		service.send(request); 													// değşiklikleri yapılan input, takipçiye send fonksiyonuyla gönderilir.
	}
	
	/**
	 * 
	 * Web servisinize mesaj gönderen takipçinin "sender" bilgisini kullanarak takipçiye çoklu RMM gönderir. TES API'sinin tek takipçiye mesaj gönderirken 
	 * kabul ettiği mesaj formatı olan TesSingleUserRequest modeline gönderilecek bilgiler setlenir. Gönderilecek fotoğraf önce FTS API kullanılarak
	 * Turkcell serverlarına yüklenir ve FTS API'den dönülen URL bilgisi, TesSingleUserRequest modelinin mesaj alınana set edilir.
	 * @param sender	takipçinin adres bilgisi (telefon numarası - varsayılan olarak karıştırılmış(opaque) numara)
	 * @param type		takipçinin web servisinize gönderdiği mesaj tipi
	 * @param ftsImageUrls	takipçiye gönderilecek çoklu RMM'deki fotoğrafların URL'sini tutan liste
	 */
	public void respondWithMultiRMM(String sender, String type, List<String> ftsImageUrls) throws IOException, URISyntaxException {
		
		TesSingleUserRequest request	=	new TesSingleUserRequest();
		Service service = new Service();
		
		String fileType						=	AppConstant.PHOTO_TYPE;
		Map<String, Object>		imageMap;
		
		Composition 	composition 		= 	new Composition();
		List<Content> 	contents			=	new ArrayList<Content>();
		Content			content 			=	new Content();
		
		Integer ctype = Ctype.fromCharToInteger(type);	
		
		content.setType(ctype);								
		content.setRichMediaType(AppConstant.MULTI_RMM); 	
		
		List<RichMedia> richmedialist 		=	new ArrayList<RichMedia>(); 	
		
		for(String ftsImageUrl : ftsImageUrls) {													// Çoklu RMM olduğundan her bir fotoğraf için richmedia oluşturulur
					
			RichMedia		richmedia		=	new RichMedia(); 								    // Her bir fotoğraf FTS API ile serverlara yüklenir
			imageMap						=	new Upload().uploadToFts(fileType, ftsImageUrl);	// FTS'e yüklendikten sonra dönen Map, url ve size bilgileri almak için kullanılır
			
			richmedia.setTitle("Bip API");   														// Her fotoğrafın yanında gözükecek olan isim									
			richmedia.setImage(imageMap.get("url").toString()); 									// Her fotoğrafın servar'a yüklendiği URL		
			richmedia.setRatio(1.0F);																// Her fotoğrafın ratio'su
			richmedia.setUrl("http://www.turkcell.com.tr/bip");										// Her fotoğrafa tıklandığında yönendireleceği URL
			
			richmedialist.add(richmedia); 															// Medya, listeye eklendi
		
		}
		
		content.setRichMediaList(richmedialist); 								
		contents.add(content); 													
		
		composition.setList(contents); 											
		
		request.setTxnid(UUID.randomUUID().toString());
		request.setReceiver(new Receiver(AppConstant.USER_NUMBER_TYPE, sender));
		request.setComposition(composition);								  
		
		service.send(request); 													
	}
	
	/**
	 * 
	 * Web servisinize mesaj gönderen takipçinin "sender" bilgisini kullanarak takipçiye anket RMM gönderir. TES API'sinin tek takipçiye mesaj gönderirken 
	 * kabul ettiği mesaj formatı olan TesSingleUserRequest modeline gönderilecek bilgiler setlenir. Gönderilecek fotoğraf önce FTS API kullanılarak
	 * Turkcell serverlarına yüklenir ve FTS API'den dönülen URL bilgisi, TesSingleUserRequest modelinin mesaj alınana set edilir.
	 * @param sender	takipçinin adres bilgisi (telefon numarası - varsayılan olarak karıştırılmış(opaque) numara)
	 * @param type		takipçinin web servisinize gönderdiği mesaj tipi
	 * @param message	takipçiye gönderilecek anketteki fotoğrafın URL'i
	 */
	public void respondWithPollRMM(String sender, String type, String message) throws IOException, URISyntaxException {
		
		TesSingleUserRequest request	=	new TesSingleUserRequest();
		Service service = new Service();
	
		String fileType					=	AppConstant.PHOTO_TYPE;
		Map<String, Object> imageMap	= 	new Upload().uploadToFts(fileType, message);

		String ftsImageUrl 				= 	imageMap.get("url").toString();

		Composition 	composition 	= 	new Composition();
		List<Content> 	contents		=	new ArrayList<Content>();
		Content			content 		=	new Content();
		
		Integer ctype = Ctype.fromCharToInteger(type);	
		
		content.setType(ctype);													// RMM tipi setlendi.
		content.setRichMediaType(AppConstant.POLL_RMM); 						// Anket tipi setlendi.
		
		List<RichMedia> richmedialist 	=	new ArrayList<RichMedia>(); 		// gönderilecek olan rich media listesi
		RichMedia		richmedia		=	new RichMedia(); 					// eklenecek rich media
		
		richmedia.setPollid("Merhaba Dünya");							        // anket id setlendi.
		richmedia.setTitle("Bip API");   										// anket ismi belirlendi.
		richmedia.setImage(ftsImageUrl); 										// anket fotoğrafı ayarlandı. Link FTS'e yüklü olmalıdır.
		richmedia.setRatio(1.0F);												// ratio ayarlandı.
		richmedia.setDescription("Bip API'yi nasıl buldun?");					// açıklama girildi.
		richmedia.setPollendtime("29.09.2016 10:55:00.000 +0300"); 				// anket bitiş tarihi girildi. Burayı ileri tarihe set etmeniz gerek. Formatı dd.MM.yyyy HH:mm:ss.SSS ZZZZ olmalıdır.
		
		List<Option> options			=	new ArrayList<Option>();			// anket seçenekleri ayarlanıyor. En az 2, en fazla 4 seçenek eklenebilir.
		options.add(new Option(1, "Çok iyi!"));
		options.add(new Option(2, "İyi"));
		options.add(new Option(3, "Daha iyi olabilir"));
		
		richmedia.setOptions(options); 											// seçenekler ankete eklendi.
		richmedialist.add(richmedia); 											// anket listeye eklendi.
		
		content.setRichMediaList(richmedialist); 								// liste içeriğe eklinde.
		contents.add(content); 													// içerik listeye eklendi.
		
		composition.setList(contents); 											// liste compostion'a eklendi.
		
		request.setTxnid(UUID.randomUUID().toString());
		request.setReceiver(new Receiver(AppConstant.USER_NUMBER_TYPE, sender));
		request.setComposition(composition);								    // composition gönderilecek requeste eklendi.
		
		service.send(request); 													// değşiklikleri yapılan input, takipçiye send fonksiyonuyla gönderilir.
	}
	
}
