package com.turkcell.bipai.merchantsimulator.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.turkcell.bipai.merchantsimulator.api.payment.model.Item;
import com.turkcell.bipai.merchantsimulator.api.payment.request.PayRequest;
import com.turkcell.bipai.merchantsimulator.api.payment.response.PayServiceResponse;
import com.turkcell.bipai.merchantsimulator.api.tes.model.Content;
import com.turkcell.bipai.merchantsimulator.api.tes.model.Ctype;
import com.turkcell.bipai.merchantsimulator.api.tes.model.TesInput;
import com.turkcell.bipai.merchantsimulator.service.PaymentService;
import com.turkcell.bipai.merchantsimulator.service.TesService;
import com.turkcell.bipai.merchantsimulator.util.AppConstants;
import com.turkcell.bipai.merchantsimulator.util.ChannelHolder;
import com.turkcell.bipai.merchantsimulator.util.ConfigLoader;
import com.turkcell.bipai.merchantsimulator.util.Products;
import com.turkcell.bipai.merchantsimulator.util.TransactionHolder;

public class CallbackWorker extends Thread {
	private static final Logger logger = Logger.getLogger(CallbackWorker.class.getName());
	private TesInput biPTesInput;

	public CallbackWorker(TesInput biPTesInput) {
		super();
		this.biPTesInput = biPTesInput;
	}

	/*
	 * Bip Sunucularına hazırladığımız listener bu methodu çağırır. Kullanıcıdan
	 * gelen mesajın yorumlandığı method burasıdır.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		String sender = biPTesInput.getSender();
		Integer msgid = biPTesInput.getMsgid();
		String sendtime = biPTesInput.getSendtime();
		String type = biPTesInput.getType();

		String ctype = biPTesInput.getCtype();
		String content = biPTesInput.getContent();
		Integer pollid = biPTesInput.getPollid();
		Integer optionid = biPTesInput.getOptionid();

		String event = biPTesInput.getEvent();
		/*
		 * Bip servisinden gelen isteğin bir mesaj mı yoksa bildirim mi olduğu
		 * kontrol ediliyor.
		 */
		if ("M".equals(type)) {
			logger.info("TES API'den takipçinin gönderdiği mesaj alınıyor...");
			logger.info("İSTEK : " + "sender: " + sender + " - msgid: " + msgid + " - sendtime: " + sendtime
					+ " - type: " + type + " - ctype: " + ctype + " - content: " + content + " - pollid: " + pollid
					+ " - optionid: " + optionid);
			Ctype ctypeText = Ctype.fromCharToText(ctype);
			switch (ctypeText) {
			case Text:
				logger.info("Takipçiden Text tipinde mesaj alındı.");
				
				if(content.trim().toLowerCase().contains("channel=")){

					String idSTR=content.toLowerCase().replace("channel=", "").trim();
					logger.info(sender+" channel idsini "+idSTR+" olarak değiştirmek istiyor.");
					int id=Integer.parseInt(idSTR);
					ChannelHolder.setChannelID(sender, id);
					List<Content> contents = new ArrayList<Content>();
					Content textContent = new Content();
					textContent.setType(0);
					textContent.setMessage("Channel ID: "+idSTR+" olarak güncellendi.");
					contents.add(textContent);

					TesService.sendContent(contents, sender);
					
				}else if (content.toLowerCase().equals("yardım") || content.toLowerCase().equals("yardim")) {
					/*
					 * Yardım mesajı geldiğinde kullanıcıya iletilecek mesaj burada
					 * alınır,yorumlanır ve cevap döner *
					 */
					List<Content> contents = new ArrayList<Content>();
					Content textContent = new Content();
					textContent.setType(0);
					textContent.setMessage("Sipariş vermek için siparişinizi virgül ile ayırıp mesaj atın.");
					contents.add(textContent);

					Content sampleOrder = new Content();
					sampleOrder.setType(0);
					sampleOrder.setMessage("Örneğin;\n'1 King Menu,2 Sprite'");
					contents.add(sampleOrder);

					TesService.sendContent(contents, sender);
				} else {
					/*
					 * Mesaj eğer yardım mesajı değil ise sipariş mesajı olduğu
					 * varsayılıyor.
					 */
					logger.info("Takipçiden sipariş isteği alındı.");
					// Config dosyasında var olan ürünlere göre mesaj
					// yorumlanıyor
					List<Item> itemList = Products.orderParser(content);

					if (itemList.isEmpty()) {
						List<Content> contents = new ArrayList<Content>();
						Content textContent = new Content();
						textContent.setType(0);
						textContent.setMessage("Siparişinizi tam anlayamadım.");
						contents.add(textContent);

						Content sampleOrder = new Content();
						sampleOrder.setType(0);
						sampleOrder.setMessage("Örneğin;\n'1 King Menu,2 Sprite'\nformatında tekrar deneyiniz.");
						contents.add(sampleOrder);

						TesService.sendContent(contents, sender);
					} else {
						// Ürün bulunması durumunda payment servisine request
						// hazırlanıyor.
						PayRequest request = new PayRequest();
						request.setTransactionId(String.valueOf((int) (System.currentTimeMillis() & 0xfffffff)));
						request.setChannelId(ChannelHolder.getChannelID(sender));
						request.setPaymentMethod("reserve");
						request.setMsisdn(sender);
						request.setIsDeliverable(String.valueOf(true));
						request.setItemList(itemList);
						
						
						List<Content> contents = new ArrayList<Content>();
						Content textContent = new Content();
						textContent.setType(0);
						textContent.setMessage("Ödeme ekranınız hazırlanıyor.");
						contents.add(textContent);
						TesService.sendContent(contents, sender);
						PayServiceResponse response = PaymentService.send(request);
						// Test amaclı olduğu için db gibi davranan bir array a
						// numara(msisdn) ve transaction id ekleniyor
						TransactionHolder.put(request.getTransactionId(), sender);
						if (response.equals(null)) {
							logger.warning("Payment Service Response null geldi.");
							contents = new ArrayList<Content>();
							textContent = new Content();
							textContent.setType(0);
							textContent.setMessage("Bir hata ile karşılaşıldı.");
							contents.add(textContent);

							TesService.sendContent(contents, sender);
						} else {
							if (response.getResultCode() == 1103) {
								//Payment requestin başarılı olduğu durum.
								logger.info("Payment request başarılı bir şekilde iletildi.");
								
							} else {
								contents = new ArrayList<Content>();
								logger.info("Payment request sonucu hatalı.ResultCode: " + response.getResultCode()
										+ " ResultDesc: " + response.getResultDesc());
								if (ConfigLoader.PAY_LIST_RESP_MAP
										.containsKey(String.valueOf(response.getResultCode()))) {
									textContent = new Content();
									textContent.setType(0);
									textContent.setMessage(ConfigLoader.PAY_LIST_RESP_MAP
											.get(String.valueOf(response.getResultCode())));
									contents.add(textContent);
								} else {
									textContent = new Content();
									textContent.setType(0);
									textContent
											.setMessage(response.getResultDesc());
									contents.add(textContent);
								}
								TesService.sendContent(contents, sender);
							}

						

						}

					}
				}

				break;

			default:
				//Metin mesajı haricindeki tüm mesaj tiplerine dönen cevap.
				logger.info("Takipçiden text olmayan bir istek alındı.");
				List<Content> contents = new ArrayList<Content>();
				Content textContent = new Content();
				textContent.setType(0);
				textContent.setMessage("Bu servis sadece metin mesajlar ile kullanılmaktadır.");
				contents.add(textContent);

				TesService.sendContent(contents, sender);
				break;
			}
		} else if ("E".equals(type)) {
			logger.info("TES API'den bildirim alınıyor...");
			logger.info("CEVAP: " + "sender: " + sender + " - msgid: " + msgid + " - sendtime: " + sendtime
					+ " - type: " + type + " - event: " + event);
			if (event.equals("U"))
				logger.info("Bir takipçi kaybettiniz.");
			if (event.equals("S"))
				logger.info("Yeni bir takipçiniz var.");
		}
	}

}
