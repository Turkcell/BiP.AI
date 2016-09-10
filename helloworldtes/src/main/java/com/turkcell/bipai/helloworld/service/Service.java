package com.turkcell.bipai.helloworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.turkcell.bipai.helloworld.model.fts.response.FtsResponse;
import com.turkcell.bipai.helloworld.model.tes.request.TesMultiUserListRequest;
import com.turkcell.bipai.helloworld.model.tes.request.TesMultiUserRequest;
import com.turkcell.bipai.helloworld.model.tes.request.TesSingleUserRequest;
import com.turkcell.bipai.helloworld.model.tes.response.TesMultiUserListRepsonse;
import com.turkcell.bipai.helloworld.model.tes.response.TesMultiUserResponse;
import com.turkcell.bipai.helloworld.model.tes.response.TesSingleUserResponse;
import com.turkcell.bipai.helloworld.util.AppConstant;
import com.turkcell.bipai.helloworld.util.BasicAuthRestTemplate;

public class Message {
	private static final Logger logger = LoggerFactory.getLogger(Message.class);
	/**
	 * TES servisinin sendmsgserv metodunu kullanarak, request nesnesi içinde belirtilen bir kullanıcıya veya 
	 * servisin tüm takipçilerine mesaj/mesaj dizisi gönderir. Tek kullanıcıya mesaj gönderilmek isteniyorsa Receiver nesnesinin
	 * type değişkeni "0" (Karıştırılmış-Opaque numara) veya "2" (MSISDN - gerçek numara) olmalı, tüm takipçilere gönderilmek 
	 * isteniyorsa "1" olmalıdır. USER ve PASS bilgilerini size ait bilgilerle değiştirmeniz gerekmektedir.
	 * 
	 * @param request JSON olarak gönderilecek olan TesSingleUserRequest objesidir.
	 * 
	 * @see <a href="http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/">http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/</a>
	 */
	protected void send(TesSingleUserRequest request) {
		
		RestTemplate	restTemplate	=	new BasicAuthRestTemplate(AppConstant.USER, AppConstant.PASS);
		logger.info("REQUEST_JSON: " + new Gson().toJson(request));
		
		try{
			TesSingleUserResponse response		=	restTemplate.postForObject(AppConstant.SINGLE_USER, request, TesSingleUserResponse.class);
			logger.info("Result Code: " + response.getResultcode());
		}
		catch(HttpClientErrorException e) {
			logger.info(e.getResponseBodyAsString());
		}
		
	}
	
	/**
	 * TES servisinin sendmsgservlist metodunu kullanarak, request nesnesi içinde belirtilen birden fazla kullanıcıya aynı mesaj/mesaj dizisini gönderir. 
	 * USER ve PASS bilgilerini size ait bilgilerle değiştirmeniz gerekmektedir.
	 * 
	 * @param request JSON olarak gönderilecek olan TesMultiUserListRequest objesidir.
	 * 
	 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/</a>
	 */
	protected void send(TesMultiUserListRequest request) {
		
		RestTemplate	restTemplate	=	new BasicAuthRestTemplate(AppConstant.USER, AppConstant.PASS);
		logger.info("REQUEST_JSON: " + new Gson().toJson(request));
		
		try{
			TesMultiUserListRepsonse response		=	restTemplate.postForObject(AppConstant.MULTI_USER_LIST, request, TesMultiUserListRepsonse.class);
			logger.info("Result Code: " + response.getResultcode() + " Report:" + response.getReport());
		}
		catch(HttpClientErrorException e) {
			logger.info(e.getResponseBodyAsString());
		}
		
	}
	
	/**
	 * TES servisinin sendmultiusermulticontent metodunu kullanarak, request nesnesi içinde belirtilen birden fazla kullanıcıya farklı mesaj/mesaj dizisi gönderir. 
	 * USER ve PASS bilgilerini size ait bilgilerle değiştirmeniz gerekmektedir.
	 * 
	 * @param request JSON olarak gönderilecek olan TesMultiUserRequest objesidir.
	 * 
	 * @see <a href="http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/">http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/</a>
	 */
	protected void send(TesMultiUserRequest request) {
		
		RestTemplate	restTemplate	=	new BasicAuthRestTemplate(AppConstant.USER, AppConstant.PASS);
		logger.info("REQUEST_JSON: " + new Gson().toJson(request));
		
		try{
			TesMultiUserResponse response		=	restTemplate.postForObject(AppConstant.MULTI_USER, request, TesMultiUserResponse.class);
			logger.info("Result Code: " + response.getResultcode() + " Report: " + response.getReport());
		}
		catch(HttpClientErrorException e) {
			logger.info(e.getResponseBodyAsString());
		}
		
	}
	
	/**
	 * FTS servisine Multipart-Form-Data formatında dosyayı yükler.
	 * 
	 * @param request data ve file objelerini tutan MultiValueMap
	 * @return response txnid, status (0-> başarılı, 1->başarısız) ve url bilgileri
	 * @see <a href="http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/">http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/</a>
	 */
	public FtsResponse send(MultiValueMap<String, Object> request) {
		FtsResponse response = null;
		RestTemplate	restTemplate	=	new BasicAuthRestTemplate(AppConstant.USER, AppConstant.PASS);
		logger.info("REQUEST_JSON: " + new Gson().toJson(request));
		
		try{
			response		=	restTemplate.postForObject(AppConstant.FTS, request, FtsResponse.class);
			logger.info("Result Code: " + response.getResultcode() + " URL: " + response.getUrl());
		}
		catch(HttpClientErrorException e) {
			logger.info(e.getResponseBodyAsString());
		}
		
		return response;
		
	}
}
