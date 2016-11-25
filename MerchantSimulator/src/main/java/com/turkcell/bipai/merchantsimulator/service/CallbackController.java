package com.turkcell.bipai.merchantsimulator.service;

import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.bipai.merchantsimulator.api.tes.model.TesInput;
import com.turkcell.bipai.merchantsimulator.workers.CallbackWorker;


/**
 * Servisimize BIP'ten gelen isteği karşılayan controller.
 */
@RestController
public class CallbackController {
	
	private static final Logger logger = Logger.getLogger(CallbackController.class.getName());

	
	/**
	 * Bip user'dan gelen mesajı bu method üzerinde kendi servisimize yönlendirir.
	 * İsteği gelir gelmez 200 sonucu dönmek için iş yükünü yeni bir thread'e devredip sonuç kodunu döndürür.
	 * @param bipTesInput Bip Tes input modelini alır.
	 * @return bip sunucularına 200 - OK dönerek isteği aldığımızı iletiriz.
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity callback(@RequestBody TesInput bipTesInput){
		logger.info("BIP listener methodu cagirildi.");
		
		if(bipTesInput.getSender()!= null){

			new CallbackWorker(bipTesInput).start();
		}
		return ResponseEntity.ok(null);
	}
}
