package com.turkcell.bipai.helloworld.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.turkcell.bipai.helloworld.api.fts.request.FtsRequest;
import com.turkcell.bipai.helloworld.api.fts.response.FtsResponse;

public class Upload {


	private static final Logger logger = Logger.getLogger(Upload.class.getName());
	
	/**
	 * FTS sunucusna istenen url'deki fotoğrafı/videoyu yükler ve geriye yüklenmiş resmin/fotoğrafın FTS'deki url'ini döner. 
	 * 
	 * @param	fileType FTS sunucusuna yüklenecek medyanın tipi, fotoğraf için "P", video için "V"
	 * @param 	urlToUploadAndSend FTS sunucusna yüklenecek resmin/fotoğrafın url'i
	 * @return 	imageMap dosyanın url ve size bilgilerini tutan Map dönülür.
	 * @throws 	IOException
	 * @throws 	URISyntaxException
	 * 
	 * @see <a href="http://www.bip.ai/documentations/fts-api/">FTS detaylı bilgi</a>
	 */
	public Map<String, Object> uploadToFts(String fileType, String urlToUploadAndSend) throws IOException, URISyntaxException {
		/**
		 * FTS servisinin upload methodu ile gönderilecek dosyayı FTS sunucusuna yükle ve url'ini al
		 */
		FtsRequest ftsRequestData = new FtsRequest();
		ftsRequestData.setTxnid(UUID.randomUUID().toString());	 //Random bir txn id al
		ftsRequestData.setReceiver(""); 						 // Boş bırakılacak
		ftsRequestData.setAvatarOwner(""); 						 // Boş bırakılacak
		ftsRequestData.setIsGroup("false"); 					 // false değer verilmeli
		ftsRequestData.setIsAvatar("false");					 // false değer verilmeli
		ftsRequestData.setToUser(""); 							 // Boş bırakılacak
		ftsRequestData.setFileType(fileType); 					 // fotoğraf için "P", video için "V" girilmeli

		//Gönderilecek dosyayı url'ini kullanarak byte array olarak oku. Boyut bilgilerini al. 
		URL url 				= new URL(urlToUploadAndSend);
		InputStream inputStream = url.openStream();
		byte[] byteArr 			= getBytesFromInputStream(inputStream);
		int imageSize			= byteArr.length;
		
		// file ve data bilgilerini rest çağrısına göndermek için Map'e ekle. FTS sunucusu Multipart-Form-Data kabul ettiğinden
		// file ve data MultiValueMap'e eklenir.
		final MultiValueMap<String, Object> ftsRequest = new LinkedMultiValueMap<String, Object>();
        ftsRequest.add("file", new FileMessageResource(byteArr, System.currentTimeMillis() + ".png")); // byte array'den byte array input stream oluşturulur ve isteğe eklenir.
        ftsRequest.add("data", new Gson().toJson(ftsRequestData));

		FtsResponse ftsResponse = send(ftsRequest);
		
		//Upload edilen medyanın bilgilerini bir map'e ekle ve dönüş değeri olarak gönder
		Map<String, Object> imageMap = new HashMap<String, Object>();
		imageMap.put("url", ftsResponse.getUrl());
		imageMap.put("size", imageSize);
		
		return imageMap;
	}

	/**
	 * FTS servisine dosyayı Multipart-Form-Data formatında yükler.
	 * 
	 * @param request data ve file objelerini tutan MultiValueMap
	 * @return response txnid, status (0-> başarılı, 1->başarısız) ve url bilgileri
	 * @see <a href="http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/">http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/</a>
	 */
	private FtsResponse send(MultiValueMap<String, Object> request) {
		FtsResponse response 			= 	null;
		RestTemplate	restTemplate	=	new BasicAuthRestTemplate(AppConstant.USER, AppConstant.PASS);
		logger.info("Dosya sunucuya yükleniyor..");
		logger.info("Gönderilen JSON: " + new Gson().toJson(request));
		
		try{
			response					=	restTemplate.postForObject(AppConstant.FTS_URI, request, FtsResponse.class);
			logger.info("Dosya başarıyla yüklendi!");
			logger.info("Result Code: " + response.getResultcode() + " URL: " + response.getUrl());
		}
		catch(HttpClientErrorException e) {
			// Değerler serverin beklediği şekilde gönderilmediyse hata döner, bu hatalar yakalanır. 
			// Doğrulama hata kodalır için http://www.bip.ai/documentations/dogrulama-hata-kodlari/
			logger.info("Doğrulama Hata kodu aldınız.");
			logger.info("Hata: " + e.getResponseBodyAsString());
			logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/content-type-ozelindeki-kontroller/");
		}
		
		return response;
		
	}
	
	/**
	 * Inputstream olarak verilen dosyayı okur ve btye[] olarak geri döndürür. 
	 * @param  okunacak input stream
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
