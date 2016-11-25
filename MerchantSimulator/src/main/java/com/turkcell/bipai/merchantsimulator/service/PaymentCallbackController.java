package com.turkcell.bipai.merchantsimulator.service;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.bipai.merchantsimulator.api.payment.model.PaymentInput;
import com.turkcell.bipai.merchantsimulator.api.payment.response.PayListenerResponse;
import com.turkcell.bipai.merchantsimulator.workers.PaymentCallbackWorker;

@RestController
public class PaymentCallbackController {
	private static final Logger logger = Logger.getLogger(PaymentCallbackController.class.getName());


	/**
	 * Payment Servis Listener methodu.
	 * Gelen isteğe hızlı cevap dönebilmek adına bu istek başka bir thread'e yönlendirilip cevap döndürülür.
	 * @param bipPayInput Payment Input Model
	 * @return payment servisine success sonucu döndürülür.
	 */
	@RequestMapping(value = "/paymentcallback", method = RequestMethod.POST, produces = "application/json")
	public PayListenerResponse callback(@RequestBody PaymentInput bipPayInput){
		logger.info(bipPayInput.getTransactionId()+ " id için payment callback methodu çağırıldı");
		new PaymentCallbackWorker(bipPayInput).start(); // İş yükünü üstlenen thread.
		PayListenerResponse response=new PayListenerResponse();
		response.setResultCode(0);
		response.setResultDesc("SUCCESS");
		response.setTransactionId(bipPayInput.getTransactionId());
		return response;
	}
}
