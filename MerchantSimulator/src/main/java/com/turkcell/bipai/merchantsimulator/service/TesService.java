package com.turkcell.bipai.merchantsimulator.service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.turkcell.bipai.merchantsimulator.api.tes.model.Composition;
import com.turkcell.bipai.merchantsimulator.api.tes.model.Content;
import com.turkcell.bipai.merchantsimulator.api.tes.model.Receiver;
import com.turkcell.bipai.merchantsimulator.api.tes.request.TesSingleUserRequest;
import com.turkcell.bipai.merchantsimulator.api.tes.response.TesSingleUserResponse;
import com.turkcell.bipai.merchantsimulator.util.AppConstants;
import com.turkcell.bipai.merchantsimulator.util.BasicAuthRestTemplate;

public class TesService {
	private static final Logger logger = Logger.getLogger(TesService.class.getName());
	
	/**
	 * Oluşturulan tes isteğini bip api'ya iletilmesinde görev alır.
	 * İsteğin header kısmına basic authentication bilgisini ekleme operasyonu burada gerçekleştirilir.
	 * @param request Tes Servis Request
	 */
	public static void send(TesSingleUserRequest request) {
		
		RestTemplate	restTemplate			=	new BasicAuthRestTemplate(AppConstants.USER, AppConstants.PASS);
		logger.info("Tek kullanıcıya mesaj gönderiliyor..");
		logger.info("Gönderilen JSON: " + new Gson().toJson(request));
		
		try{
			TesSingleUserResponse response		=	restTemplate.postForObject(AppConstants.TES_SINGLE_USER, request, TesSingleUserResponse.class);
			if(response.getResultcode() == 0)
				logger.info("Mesaj başarıyla gönderildi!");
			else {
				logger.info("Hata kodu aldınız.");
				logger.info("resultcode: " + response.getResultcode());
				logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/hata-kodlari/");
			}
		}
		catch(HttpClientErrorException e) {
			logger.info("Doğrulama Hata kodu aldınız.");
			logger.info("Hata: " + e.getResponseBodyAsString());
			logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/content-type-ozelindeki-kontroller/");
		}
		
	}
	
	/**
	 * Oluşturulan mesaj listesini açık numaraya iletmeyi sağlar.
	 * @param contents mesaj listesi
	 * @param msisdn açık numara olmalıdır.(Ör. 90532XXXXXXX)
	 */
	public static void sendContent(List<Content> contents,String msisdn){
		TesSingleUserRequest request = new TesSingleUserRequest();
		Composition composition = new Composition();
		composition.setList(contents);
		request.setTxnid(UUID.randomUUID().toString());
		request.setReceiver(new Receiver(2, msisdn));
		request.setComposition(composition);
		send(request);
	}
}
