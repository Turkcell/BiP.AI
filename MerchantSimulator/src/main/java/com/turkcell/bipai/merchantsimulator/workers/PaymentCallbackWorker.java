package com.turkcell.bipai.merchantsimulator.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.turkcell.bipai.merchantsimulator.api.payment.model.PaymentInput;
import com.turkcell.bipai.merchantsimulator.api.payment.request.PayCommitRequest;
import com.turkcell.bipai.merchantsimulator.api.payment.response.PayServiceResponse;
import com.turkcell.bipai.merchantsimulator.api.tes.model.Content;
import com.turkcell.bipai.merchantsimulator.service.PaymentService;
import com.turkcell.bipai.merchantsimulator.service.TesService;
import com.turkcell.bipai.merchantsimulator.util.AppConstants;
import com.turkcell.bipai.merchantsimulator.util.ChannelHolder;
import com.turkcell.bipai.merchantsimulator.util.ConfigLoader;
import com.turkcell.bipai.merchantsimulator.util.TransactionHolder;

public class PaymentCallbackWorker extends Thread {
	private static final Logger logger = Logger.getLogger(PaymentCallbackWorker.class.getName());
	private PaymentInput paymentInput;

	public PaymentCallbackWorker(PaymentInput input) {
		super();
		this.paymentInput = input;
	}
	/*
	 *Payment servisinin bizi çağırdığı ve yorumlandığı method burasıdır. 
	 */
	@Override
	public void run() {
		super.run();
		logger.info("Payment listener method çağırıldı.");
		logger.info(paymentInput.toString());
		
		// Memoryde oluşturduğumuz transactionID-msidn mapini kontrol ediyoruz.
		if (TransactionHolder.doesTransactionIDexist(paymentInput.getTransactionId())) {
			String msisdn=TransactionHolder.getMsisdn(paymentInput.getTransactionId());
			logger.info(paymentInput.getTransactionId()+" id li transaction memoryde bulundu.(msisdn:"+msisdn+" channelID="+ChannelHolder.getChannelID(msisdn));
			//10. kanal özel bir durum içeriyor. 
			//Payment Servisinin bize gönderdiği additionalInfo kısmındaki commit token ı geri gönderiyoruz.
			if (paymentInput.getChannelId()==10) {
				paymentInput.getAdditionalInfo().getCommitToken();
				PayCommitRequest request = new PayCommitRequest();
				request.setTransactionId(paymentInput.getTransactionId());
				request.setChanneId(paymentInput.getChannelId());
				request.setCommitToken(paymentInput.getAdditionalInfo().getCommitToken());
				request.setMsisdn(msisdn);
				PayServiceResponse response=PaymentService.send(request);
				logger.info(response.toString());
				List<Content> contents = new ArrayList<Content>();
				Content textContent = new Content();
				textContent.setType(0);
				if (response.getResultCode() == 0) {
					textContent.setMessage(
							ConfigLoader.PAY_LIST_RESP_MAP.get(String.valueOf(response.getResultCode())));

				} else {
					if (ConfigLoader.PAY_LIST_RESP_MAP.containsKey(String.valueOf(response.getResultCode()))) {
						textContent.setMessage(
								ConfigLoader.PAY_LIST_RESP_MAP.get(String.valueOf(response.getResultCode())));
					} else {
						textContent.setMessage(response.getResultDesc());
					}
				}
				contents.add(textContent);
				logger.info( msisdn + " kullanıcısına payment geri bildirim mesajı gönderiliyor.");
				TesService.sendContent(contents, msisdn);
				TransactionHolder.remove(response.getTransactionId());
				
			}else{
				//Diğer tüm kanallar için süreç aynı, dönen mesajı yorumlayıp kullanıcımıza geri dönüyoruz.
				List<Content> contents = new ArrayList<Content>();
				Content textContent = new Content();
				textContent.setType(0);
				if (paymentInput.getResultCode() == 0) {
					textContent.setMessage(
							ConfigLoader.PAY_LIST_RESP_MAP.get(String.valueOf(paymentInput.getResultCode())));

				} else {
					if (ConfigLoader.PAY_LIST_RESP_MAP.containsKey(String.valueOf(paymentInput.getResultCode()))) {
						textContent.setMessage(
								ConfigLoader.PAY_LIST_RESP_MAP.get(String.valueOf(paymentInput.getResultCode())));
					} else {
						textContent.setMessage(paymentInput.getResultDesc());
					}
				}
				contents.add(textContent);
				logger.info(msisdn+" kullanıcısına payment geri bildirim mesajı gönderiliyor." );
				TesService.sendContent(contents,msisdn);
				TransactionHolder.remove(paymentInput.getTransactionId());

			
			}
		} else {
			logger.info(paymentInput.getTransactionId()+" id li transaction memoryde bulunamadı.");
		}

	}

}
