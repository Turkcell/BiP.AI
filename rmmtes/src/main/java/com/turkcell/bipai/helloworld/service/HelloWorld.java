package com.turkcell.bipai.helloworld.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.turkcell.bipai.helloworld.model.BiPFtsMessageRequest;
import com.turkcell.bipai.helloworld.model.BiPFtsMessageResponse;
import com.turkcell.bipai.helloworld.model.BiPTesInput;
import com.turkcell.bipai.helloworld.model.BiPTesPushMessageRequest;
import com.turkcell.bipai.helloworld.model.BiPTesPushMessageResponse;
import com.turkcell.bipai.helloworld.model.Content;
import com.turkcell.bipai.helloworld.model.Ctype;
import com.turkcell.bipai.helloworld.model.Receiver;
import com.turkcell.bipai.helloworld.model.RichMedia;
import com.turkcell.bipai.helloworld.util.BasicAuthRestTemplate;


/**
 * 
 * TES API'yi kullanarak "sender" parametresi ile belirtilen kullanıcıya RMM gönderir. 
 * @author BiP AI
 *
 */
@RestController
public class HelloWorld {
	
	//TES ve FTS servislerine bağlanacak kullanıcı. Kendi kullanıcınız ile değiştirin.
	private final String user			= "user"; 
	//Kendi şifreniz ile değiştirin.
	private final String password		= "password";
	// TES service URL
	private final String tesServiceUrl 	= "https://prptims.turkcell.com.tr/tes/rest/spi/sendmsgservlist";
	// FTS service URL
	private final String ftsServiceUrl	= "http://prptims.turkcell.com.tr/fts/rest/file/upload"; 
	
	private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
	
	/**
	 * 
	 * Talep (request) gövdesinden JSON formatında sender, type, content gibi bilgileri alarak content tipine göre cevap (response) oluşturur 
	 * ve kullanıcıya gönderilmesini sağlayan {@link #respondRMM(String, String)} metodunu çağırır. 
	 * @param biPTesInput JSON formatında giriş (Ör: {"type": "2", "sender": "90532XXXXXXX", "ctype": "R", "content": "", "msgid": 12, "sendtime": ""})
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.POST, produces = "application/json")
	public void hello(@RequestBody BiPTesInput biPTesInput) throws IOException, URISyntaxException {
		
		String	sender		=	biPTesInput.getSender();
		Integer	msgid		=	biPTesInput.getMsgid();
		String	sendtime	=	biPTesInput.getSendtime();
		String	type		=	biPTesInput.getType();
		String	ctypeVal	=	biPTesInput.getCtype();
		String	content		=	biPTesInput.getContent();
		
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
				respondRMM(sender, type);
				break;
			case Sticker:
				logger.info("Sticker input message");
				break;
			case Text:
				logger.info("Text input message");
				break;
			case Video:
				logger.info("Video input message");
				break;
			default:
				break;
		}
	}

	
	/**
	 * TES servisinin sendmsgservlist metodunu kullanarak, sender ile belirtilen kullanıcıya RMM (Rich Media Message) mesajı gönderir.
	 * username ve password bilgilerini size ait bilgiler ile değiştirmeniz gerekmektedir. 
	 * @param sender Mesajın gönderileceği kişiyi ifade eden metin
	 * @param type sender tipi 
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * 
	 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile</a>
	 */
	private void respondRMM(String sender, String type) throws IOException, URISyntaxException {
		String imageUrlToUploadAndSend	= "http://bip.ai/wp-content/themes/bip-developers/assets/images/logo-cogs.png"; //FTS'e yüklenecek ve kullanıcıya gönderilecek dosyanın url'i.

		String 						ftsImageUrl		= uploadImageToFts(imageUrlToUploadAndSend);
		RestTemplate				restTemplate	= new BasicAuthRestTemplate(user, password);
		BiPTesPushMessageRequest	request			= new BiPTesPushMessageRequest();
		
		RichMedia richMedia = new RichMedia();
		richMedia.setTitle("Bu bir başlıktır.");
		/*
		 * richMedia.setImage metoduna geçilen url FTS'e yüklenmiş bir resmin url'i olmalıdır. 
		 * FTS'e nasıl resim yüklenebileceğine dair örnek için 
		 * https://github.com/Turkcell/BiP.AI/tree/master/imageuploadsendtes linkini inceleyebilirsiniz. 
		 */
		richMedia.setImage(ftsImageUrl);
		richMedia.setDescription("Bu bir açıklamadır.");
		richMedia.setUrl("http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile");
		richMedia.setUrltext("Bu bir linktir. Daha fazla bilgi için tıklayınız...");
		richMedia.setRatio(1);
		
		request.setTxnid(UUID.randomUUID().toString());
		request.setContent (new Content (8, 0, new RichMedia[]{richMedia}));
		request.setReceivers(new Receiver[]{new Receiver(Integer.parseInt(type), sender)});
		
		logger.info("Request Json: " + new Gson().toJson(request));
		
		BiPTesPushMessageResponse	response		=	restTemplate.postForObject(tesServiceUrl, request, BiPTesPushMessageResponse.class);
		
		logger.info("Result Code: " + response.getResultcode());
	}
	
	/**
	 * FTS sunucusna istenen url'deki resmi yükler ve geriye yüklenmiş resmin FTS'deki url'ini döner. 
	 * 
	 * @param imageUrlToUploadAndSend FTS sunucusna yüklenecek resmin url'i
	 * @param user fts user bilgisi
	 * @param password fts password bilgisi
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private String uploadImageToFts(String imageUrlToUploadAndSend) throws IOException, URISyntaxException {
		/**
		 * FTS servisinin upload methodu ile gönderilecek dosyayı FTS sunucusuna yükle ve url'ini al
		 */
		BiPFtsMessageRequest ftsRequestData = new BiPFtsMessageRequest();
		ftsRequestData.setTxnid(UUID.randomUUID().toString()); //Random bir txn id al
		ftsRequestData.setReceiver("");
		ftsRequestData.setAvatarOwner("");
		ftsRequestData.setIsGroup("false");
		ftsRequestData.setIsAvatar("false");
		ftsRequestData.setToUser("");
		ftsRequestData.setFileType("P");

		//Gönderilecek dosyayı url'ini kullanarak oku. Boyut bilgilerini al. 
		URL url 				= new URL(imageUrlToUploadAndSend);
		InputStream inputStream = url.openStream();
		byte[] byteArr 			= getBytesFromInputStream(inputStream);
		
		//file ve data bilgilerini rest çağrısına göndermek için Map'e ekle. 
		final MultiValueMap<String, Object> ftsRequest = new LinkedMultiValueMap<String, Object>();
        ftsRequest.add("file", new com.turkcell.bipai.helloworld.model.FileMessageResource(byteArr, System.currentTimeMillis() + ".png"));
        ftsRequest.add("data", new Gson().toJson(ftsRequestData));

		logger.info("Request json: " + new Gson().toJson(ftsRequest));
		RestTemplate			restTemplate	= new BasicAuthRestTemplate(user, password);
		BiPFtsMessageResponse	ftsResponse		= restTemplate.postForObject(ftsServiceUrl, ftsRequest, BiPFtsMessageResponse.class);
		logger.info("Result code: " + ftsResponse.getResultcode() + " | " + ftsResponse.getUrl());
		
		return ftsResponse.getUrl();
	}	

	/**
	 * Inputstream olarak verilen dosyayı okur ve btye[] olarak geri döndürür. 
	 * @param is okunacak input stream
	 * @return okunan input stream'i btye[] olarak geri döndürür.
	 * @throws IOException
	 */
	private static byte[] getBytesFromInputStream(InputStream is) throws IOException
	{
		try (ByteArrayOutputStream os = new ByteArrayOutputStream();)
		{
			byte[] buffer = new byte[0xFFFF];

			for (int len; (len = is.read(buffer)) != -1;)
				os.write(buffer, 0, len);
			os.flush();

			return os.toByteArray();
		}
	}
}
