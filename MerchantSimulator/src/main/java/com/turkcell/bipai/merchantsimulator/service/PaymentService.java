package com.turkcell.bipai.merchantsimulator.service;

import java.util.logging.Logger;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.turkcell.bipai.merchantsimulator.api.payment.request.PayCommitRequest;
import com.turkcell.bipai.merchantsimulator.api.payment.request.PayRequest;
import com.turkcell.bipai.merchantsimulator.api.payment.response.PayServiceResponse;
import com.turkcell.bipai.merchantsimulator.util.AppConstants;
import com.turkcell.bipai.merchantsimulator.util.BasicAuthRestTemplate;

/**
 * Payment Servis.
 */
public class PaymentService {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(PaymentService.class.getName());


	
	/**
	 * Payment servisine request göndermeyi sağlar.
	 * İsteğin header kısmına basic authentication bilgisini ekleme operasyonu burada gerçekleştirilir
	 * @param 
	 * @return yorumlanmak üzere sonuç döndürülür
	 */
	public static PayServiceResponse send(PayRequest request) {

		RestTemplate restTemplate = new BasicAuthRestTemplate(AppConstants.USER, AppConstants.PASS);
		logger.info("Payment servise istek gönderiliyor.");
		logger.info("Gönderilen JSON: " + new Gson().toJson(request));

		try {
			PayServiceResponse response = restTemplate.postForObject(AppConstants.PAYMENT_SERVICE, request,
					PayServiceResponse.class);
			return response;
			
		} catch (HttpClientErrorException e) {
			logger.info("Doğrulama Hata kodu aldınız.");
			logger.info("Hata: " + e.getResponseBodyAsString());
		}
		
		return null;

	}

	/**
	 * Commit request göndermeyi sağlar.(Sadece channel id = 10 için kullanılır.)
	 * İsteğin header kısmına basic authentication bilgisini ekleme operasyonu burada gerçekleştirilir
	 * @param request the request
	 * @return the pay service response
	 */
	public static PayServiceResponse send(PayCommitRequest request) {

		RestTemplate restTemplate = new BasicAuthRestTemplate(AppConstants.USER, AppConstants.PASS);
		logger.info("Payment servise istek gönderiliyor.");
		logger.info("Gönderilen JSON: " + new Gson().toJson(request));

		try {
			PayServiceResponse response = restTemplate.postForObject(AppConstants.PAYMENT_COMMIT, request,
					PayServiceResponse.class);
			return response;
		} catch (HttpClientErrorException e) {
			logger.info("Doğrulama Hata kodu aldınız.");
			logger.info("Hata: " + e.getResponseBodyAsString());
		}
		
		return null;

	}
}
