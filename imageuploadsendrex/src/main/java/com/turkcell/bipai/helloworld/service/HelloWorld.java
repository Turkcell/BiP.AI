package com.turkcell.bipai.helloworld.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

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
import com.turkcell.bipai.helloworld.model.BiPRexInput;
import com.turkcell.bipai.helloworld.model.BiPRexMessage;
import com.turkcell.bipai.helloworld.model.BiPRexOutput;
import com.turkcell.bipai.helloworld.model.Composition;
import com.turkcell.bipai.helloworld.model.Type;
import com.turkcell.bipai.helloworld.util.BasicAuthRestTemplate;

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
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.POST, produces = "application/json")
	public BiPRexOutput hello(@RequestBody BiPRexInput bipRexInput) throws IOException, URISyntaxException {
				
		String			sender			=	bipRexInput.getSender();
		Integer			serviceId		=	bipRexInput.getServiceId();
		String			transactionId	=	bipRexInput.getTransactionId();
		String			msgId			=	bipRexInput.getMsgId();
		String			receiveDate		=	bipRexInput.getReceiveDate();
		Composition		composition		=	bipRexInput.getComposition();
		BiPRexMessage	bipRexMessage	=	composition.getList().get(0);
		String			message			=	bipRexMessage.getMessage();
		BiPRexOutput	output			=	null;
		
		logger.info("sender: " + sender + " - transactionId: " + transactionId + " - msgId: " + msgId + " - receiveDate: " + receiveDate + " - serviceId: " + serviceId + " - message: " + message);
			
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
				output = createOutput(bipRexInput);
				break;
			case Location:
				logger.info("Location input message");
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
		
		return output;
	}

	/**
	 * REX servisine cevap olarak dönülecek BiPRexOutput tipindeki nesneyi oluşturur. Bu nesne REX'e cevap olarak görünür. 
	 * 
	 * @param input Bu porperty'den gelen mesajın transaction id'si alınır ve bu id ile output oluşturulur. 
	 * @param text Dönülecek mesajın içeriğidir. 
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * 
	 * @see <a href="http://www.bip.ai/documentations/rex-api/">http://www.bip.ai/documentations/rex-api</a>
	 */
	private BiPRexOutput createOutput(BiPRexInput input) throws IOException, URISyntaxException {
		String imageUrlToUploadAndSend	= "http://bip.ai/wp-content/themes/bip-developers/assets/images/logo-cogs.png"; //FTS'e yüklenecek ve kullanıcıya gönderilecek dosyanın url'i.
		Map<String, Object> imageMap = uploadImageToFts(imageUrlToUploadAndSend);
		
		BiPRexOutput response = new BiPRexOutput(input.getTransactionId());
		
		Composition composition = new Composition();
		List<BiPRexMessage> responseList = new ArrayList<BiPRexMessage>();
		composition.setList(responseList);
		
		String uploadedFtsImageUrl = imageMap.get("url").toString();
		imageMap.remove("url");
		
		BiPRexMessage saacMessage = new BiPRexMessage();
		saacMessage.setType(2);
		saacMessage.setMessage(uploadedFtsImageUrl);
		saacMessage.setData(imageMap);
		
		responseList.add(saacMessage);
		response.setComposition(composition);
		return response;
	}
	
	/**
	 * FTS sunucusna istenen url'deki resmi yükler ve geriye yüklenmiş resmin FTS'deki url'ini döner. 
	 * 
	 * @param imageUrlToUploadAndSend FTS sunucusna yüklenecek resmin url'i
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private Map<String, Object> uploadImageToFts(String imageUrlToUploadAndSend) throws IOException, URISyntaxException {
		String user 			= "user"; //TES ve FTS servislerine bağlanacak kullanıcı. Kendi kullanıcınız ile değiştirin.
		String password			= "password"; //Kendi şifreniz ile değiştirin.
		String ftsServiceUrl	= "http://prptims.turkcell.com.tr/fts/rest/file/upload"; //FTS servis url'i. Dosya yüklemek için kullanılacak.

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
		int imageSize			= byteArr.length;
		BufferedImage bImage	= ImageIO.read(url);
		int imageWidth          = bImage.getWidth();
		int imageHeight         = bImage.getHeight();
		
		//file ve data bilgilerini rest çağrısına göndermek için Map'e ekle. 
		final MultiValueMap<String, Object> ftsRequest = new LinkedMultiValueMap<String, Object>();
        ftsRequest.add("file", new com.turkcell.bipai.helloworld.model.FileMessageResource(byteArr, System.currentTimeMillis() + ".png"));
        ftsRequest.add("data", new Gson().toJson(ftsRequestData));

		logger.info("Request json: " + new Gson().toJson(ftsRequest));
		RestTemplate			restTemplate	= new BasicAuthRestTemplate(user, password);
		BiPFtsMessageResponse	ftsResponse		= restTemplate.postForObject(ftsServiceUrl, ftsRequest, BiPFtsMessageResponse.class);
		logger.info("Result code: " + ftsResponse.getResultcode() + " | " + ftsResponse.getUrl());
		
		//Upload edilen resmin bilgilerini bir map'e ekle ve dönüş değeri olarak gönder
		Map<String, Object> imageMap = new HashMap<String, Object>();
		imageMap.put("url", ftsResponse.getUrl());
		imageMap.put("size", imageSize);
		imageMap.put("height", imageHeight);
		imageMap.put("width", imageWidth);
		
		return imageMap;
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
