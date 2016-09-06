package com.turkcell.bipai.helloworld.service;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.turkcell.bipai.helloworld.model.Upload;
import com.turkcell.bipai.helloworld.model.tes.data.Composition;
import com.turkcell.bipai.helloworld.model.tes.data.Content;
import com.turkcell.bipai.helloworld.model.tes.data.Ctype;
import com.turkcell.bipai.helloworld.model.tes.data.Option;
import com.turkcell.bipai.helloworld.model.tes.data.Receiver;
import com.turkcell.bipai.helloworld.model.tes.data.ReceiverContent;
import com.turkcell.bipai.helloworld.model.tes.data.RichMedia;
import com.turkcell.bipai.helloworld.model.tes.request.TesMultiUserListRequest;
import com.turkcell.bipai.helloworld.model.tes.request.TesMultiUserRequest;
import com.turkcell.bipai.helloworld.model.tes.request.TesSingleUserRequest;

/**
 * 
 * TES API'yi kullanarak 1 veya daha fazla içeriğin 1 kullanıcıya veya tüm takipçilere {@link #tekKisiye(TesSingleUserRequest)},
 * 1'den fazla kullanıcıya {@link #cokKisiyeAyni(TesMultiUserListRequest)}, 1'den fazla kullanıcıya farklı farklı olarak {@link #cokKisiyeFarkli(TesMultiUserRequest)}
 * gönderilmesini sağlayan örnekler.
 * @author BiP AI
 *
 */
@RestController
public class Services {
	
	private static final Logger logger = LoggerFactory.getLogger(Services.class);
	private Message message = new Message();
	
	/**
	 * 
	 * Talep (request) gövdesinden JSON formatında alıcının (Receiver) address, type ve composition list'ine eklediği 
	 * içeriğin/içeriklerin  bilgilerini alarak tek kullanıcıya/tüm takipçilere gönderilmesini sağlayan {@link #respond(TesSingleUserRequest)} metodunu çağırır.
	 * @param request JSON formatında giriş Ör: 
	 * <pre>
{
  "txnid": "200",
  "receiver": {
    "type": 2,
    "address": "05a6e402f22341e4c016302e2dca73c2"
  },
  "composition": {
    "list": [
      {
        "type": 0,
        "message": "Merhaba Dünya"
      },
      {
        "type": 2,
        "message": "http://link/image.jpg",
        "size": 133844,
        "ratio": 0.6
      }
    ]
  }
}
	 * </pre>
	 */
	@RequestMapping(value = "/tekKisiye", method = RequestMethod.POST, produces = "application/json")
	public void tekKisiye(@RequestBody TesSingleUserRequest request) {
		
		/*
		 * tekKisiye URI yına gelen içerik respond fonksiyonuna iletilirek TES API'sinin sendmsgserv fonksiyonu çağırılır.
		 * Burada gelen JSON değerlerinin setter'ları çağırılarak özelleştirilebilir, filtrelemeler koyulabilir,
		 * değiştirilebilir. 
		 */
		if (request.getTxnid() == null)
			request.setTxnid(UUID.randomUUID().toString());
		
		Receiver receiver		=	request.getReceiver();	
		logger.info(receiver.toString());
	
		Composition composition = request.getComposition();
		List<Content> contents = composition.getList();

		for(Content content : contents) {
			Integer contentType		=	content.getType();
			Ctype	contentTypeName	=	Ctype.fromCode(contentType);
			logger.info(contentTypeName + " input message");
		}

		message.send(request);
	}
	
	
	
	/**
	 * 
	 * Talep (request) gövdesinden JSON formatında alıcının (Receiver) address, type ve composition list'ine eklediği 
	 * içeriğin/içeriklerin  bilgilerini alarak çok kullanıcıya aynı gönderilmesini sağlayan {@link #respond(TesMultiUserListRuqest)} metodunu çağırır. 
	 * @param request JSON formatında giriş Ör:  
	 * <pre>
{
  "txnid": "400",
  "receivers": [
    {
      "type": 2,
      "address": "05a6e402f22341e4c016302e2dca73c2"
    },
    {
      "type": 2,
      "address": "05a6e402f22341e4c016302e2dca73c2"
    }
  ],
  "composition": {
    "list": [
      {
        "type": 0,
        "message":"Merhaba Dünya!"
      },
      {
        "type": 2,
        "message": "http://link/image.jpg",
        "size": 133844,
        "ratio": 0.6
      }
    ]
  }
}
	 * </pre>
	 */
	@RequestMapping(value = "/cokKisiyeAyni", method = RequestMethod.POST, produces = "application/json")
	public void cokKisiyeAyni(@RequestBody TesMultiUserListRequest request) {
		
		/*
		 * cokKisiyeAyni URI yına gelen içerik respond fonksiyonuna iletilirek TES API'sinin sendmsgservlist fonksiyonu çağırılır.
		 * Burada gelen JSON değerlerinin setter'ları çağırılarak özelleştirilebilir, filtrelemeler koyulabilir,
		 * değiştirilebilir.
		 */
		
		if (request.getTxnid() == null)
			request.setTxnid(UUID.randomUUID().toString());
		
		List<Receiver> receivers	=	request.getReceivers();
		
		for(Receiver receiver : receivers)
			logger.info("receiver[ address = " + receiver.getAddress() + ", type = " + receiver.getType() + "]\n");
		
		Composition composition = request.getComposition();
		List<Content> contents = composition.getList();

		for(Content content : contents) {
			Integer contentType		=	content.getType();
			Ctype	contentTypeName	=	Ctype.fromCode(contentType);
			logger.info(contentTypeName + " input message");
		}
		message.send(request);
	}
	
	
	/**
	 * 
	 * Talep (request) gövdesinden JSON formatında alıcının (Receiver) address, type ve composition list'ine eklediği 
	 * içeriğin/içeriklerin  bilgilerini alarak çok kullanıcıya farklı farklı olarak gönderilmesini sağlayan 
	 * {@link #respond(TesMultiUserRequest)} metodunu çağırır. 
	 * @param request JSON formatında giriş Ör: 
	 * <pre>
 { 
	   "txnid":100,
	   "receivercontentlist":[ 
	      { 
	         "receiver":{ 
	            "type":2,
	            "address":"9053XXXXXXXX"
	         },
	         "composition":{ 
	            "list":[ 
	               { 
	                  "message":"Merhaba Dünya",
	                  "type":0
	               },
	               { 
	                  "message":"https://prptims.turkcell.com.tr/scontent/p2p/03022016/08/Pff7af11422edb797be7015534d596d2c8846efe555cdcba06b11b09f81b9435c1.jpg",
	                  "ratio":0.5625,
	                  "type":2,
	                  "size":49628
	               }
	            ]
	         },
	         "expire":60
	      },
	      { 
	         "receiver":{ 
	            "type":2,
	            "address":"9053YYYYYYYY"
	         },
	        "composition":{ 
	            "list":[ 
	                {
	                    "type":0,
	                    "message":"Merhaba"
	                }
	            ]
	        }
	      }
	   ]
	}
	 * </pre>
	 */
	@RequestMapping(value = "/cokKisiyeFarkli", method = RequestMethod.POST, produces = "application/json")
	public void cokKisiyeFarkli(@RequestBody TesMultiUserRequest request) {
		
		/*
		 * cokKisiyeAyni URI yına gelen içerik respond fonksiyonuna iletilirek TES API'sinin sendmultiusermulticontent fonksiyonu çağırılır.
		 * Burada gelen JSON değerlerinin setter'ları çağırılarak özelleştirilebilir, filtrelemeler koyulabilir,
		 * değiştirilebilir. 
		 */
		
		if (request.getTxnid() == null)
			request.setTxnid(UUID.randomUUID().toString());
		
		List<ReceiverContent>	receivercontentlist	=	request.getReceivercontentlist();
		
		for(ReceiverContent receiverContent : receivercontentlist) {
			
			Receiver receiver = receiverContent.getReceiver();
			logger.info("receiver[ address = " + receiver.getAddress() + ", type = " + receiver.getType() + "]\n");

			Composition composition = receiverContent.getComposition();
			List<Content> contents = composition.getList();
	
			for(Content content : contents) {
				Integer contentType		=	content.getType();
				Ctype	contentTypeName	=	Ctype.fromCode(contentType);
				logger.info(contentTypeName + " input message");
			}
		}
		
		message.send(request);
	}
	

	/**
	 * Tek kullanıcıya rastgele bir mesaj gönderir.
	 * @param request receiver type ve addres gönderilmesi yeterlidir. Örnek istek: (60 saniye timer ile)
	 <pre>
  { 
	   "txnid":100,
	    "receiver":{ 
	        "type":0,
	        "address":"05a6d402f40383e4c016302e2dca75a2"
	     },
	    "expire":60
  }
	 </pre>
	 */
	@RequestMapping(value = "/ornekler/tekKisiye/metin", method = RequestMethod.POST, produces = "application/json")
	public void metin(@RequestBody TesSingleUserRequest request) {
		
		if (request.getTxnid() == null)
			request.setTxnid(UUID.randomUUID().toString()); // Txnid setlenmediyse otomatik bir değer oluşturulur.
		
		Receiver receiver			=	request.getReceiver();	
		logger.info(receiver.toString());
	
		Composition composition 	= 	new Composition(); // Her içerik lisetsi composition içerisinde gönderilir.
		List<Content> contents		=	new ArrayList<Content>(); // İçerikleri tutacak olan liste.
		Content		content 		=	new Content();	// İçerik
		
		content.setType(0); // İçerik Text mesajı, 0'a setlenir
		content.setMessage("Rastgele Mesaj = " + UUID.randomUUID().toString()); // Rastgele bir mesaj oluşturulur.
		contents.add(content); // İçerik content listesine eklenir
		
		composition.setList(contents); // Content listesi composition'a eklenir.
		request.setComposition(composition); // composition da input'a eklenir.
		
		logger.info("Request json: " + new Gson().toJson(request));
		message.send(request); // değşiklikleri yapılan input, takipçiye send fonksiyonuyla gönderilir.
	}
	
	
	/**
	 * Çok kullanıcıya tanımlanmış aynı anket mesajını gönderir.
	 * @param request receiver type ve addres gönderilmesi yeterlidir. Örnek istek:
	 <pre>
{  
   "receivers":[  
      {  
         "type":0,
         "address":"05a6d402f40383e4c016302e2dca75a2"
      },
      {  
         "type":0,
         "address":"d8077d46ae538e1866f224a843ae8088"
      }
   ]
}
	 </pre>
	 */
	@RequestMapping(value = "/ornekler/cokKisiyeAyni/anket", method = RequestMethod.POST, produces = "application/json")
	public void anket(@RequestBody TesMultiUserListRequest request) {
		
		if (request.getTxnid() == null)
			request.setTxnid(UUID.randomUUID().toString());
		
		Composition 	composition 	= 	new Composition(); // Her içerik lisetsi composition içerisinde gönderilir.
		List<Content> 	contents		=	new ArrayList<Content>(); // İçerikleri tutacak olan liste.
		Content			content 		=	new Content(); // İçerik
		
		content.setType(8); // RMM tipi setlendi.
		content.setRichmediatype(2); // Anket tipi setlendi.
		
		List<RichMedia> richmedialist 	=	new ArrayList<RichMedia>(); // gönderilecek olan rich media listesi
		RichMedia		richmedia		=	new RichMedia(); // eklenecek rich media
		
		richmedia.setPollid("Survivor-1"); // anket id setlendi.
		richmedia.setTitle("Survivor");   // anket ismi belirlendi.
		richmedia.setImage("https://testtims.turkcell.com.tr/scontent/p2p/28032016/12/survivor.jpeg"); // anket fotoğrafı ayarlandı. Link FTS'e yüklü olmalıdır.
		richmedia.setRatio(0.5F);		// ratio ayarlandı.
		richmedia.setDescription("Survivor'da kim kalsın?"); // açıklama girildi.
		richmedia.setPollendtime("06.09.2016 10:55:00.000 +0300"); // anket bitiş tarihi girildi. Formatı dd.MM.yyyy HH:mm:ss.SSS ZZZZ olmalıdır.
		
		List<Option> options			=	new ArrayList<Option>(); // anket seçenekleri ayarlanıyor. En az 2, en fazla 4 seçenek eklenebilir.
		options.add(new Option(1, "Yılmaz Morgül"));
		options.add(new Option(2, "Yunus Gence"));
		options.add(new Option(3, "Yattara"));
		
		richmedia.setOptions(options); // seçenekler ankete eklendi.
		richmedialist.add(richmedia); // anket listeye eklendi.
		content.setRichmedialist(richmedialist); // liste içeriğe eklinde.
		contents.add(content); // içerik listeye eklendi.
		composition.setList(contents); // liste compostion'a eklendi.
		request.setComposition(composition); // composition gönderilecek requeste eklendi.
		
		logger.info("Request json: " + new Gson().toJson(request));
		message.send(request); // değşiklikleri yapılan input, takipçiye send fonksiyonuyla gönderilir.
	}
	
	/**
	 * İstekte gönderilen medya URL'ini FTS sunucusuna yükleyip tek Kullanıcıya mesaj olarak gönderir.
	 * @param request
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@RequestMapping(value = "/ornekler/fts/tekKisiye", method = RequestMethod.POST, produces = "application/json")
	public void fts(@RequestBody TesSingleUserRequest request) throws IOException, URISyntaxException {
		
		String fileType					= 	request.getFileType();   // Fotoğraf için "P", Video için "V" parametresi gönderilir.
		String imageUrlToUploadAndSend	= 	request.getFileUrl(); //FTS'e yüklenecek ve kullanıcıya gönderilecek dosyanın url'i gönderilen inputtan alınır.
		Map<String, Object> imageMap	= 	new Upload().uploadToFts(fileType, imageUrlToUploadAndSend); // Dosya FTS'e upload edilir ve url,size bilgileri alınır.

		String ftsImageUrl = imageMap.get("url").toString(); // Görselin URL'i
		int size = (int)imageMap.get("size"); // Görselin boyutu

		/**
		 * FTS sunucusuna yüklenen resmi URL'ini kullanarak gönder.
		 */;
		if (request.getTxnid() == null)
			request.setTxnid(UUID.randomUUID().toString()); //Random bir txn id al
		
		Composition composition 	= 	new Composition(); // Her içerik lisetsi composition içerisinde gönderilir.
		List<Content> contents		=	new ArrayList<Content>(); // İçerikleri tutacak olan liste.
		Content		content 		=	new Content(); // İçerik
		
		
		content.setType(2); // Görsel tipi setlenir.
		content.setMessage(ftsImageUrl); // FTS'ten dönen URL setlenir.
		content.setSize(size); // Görselin boyutu
		content.setRatio(1.0f); // Görselin ratiosu
		contents.add(content); // İçerik listeye eklenir.
		
		composition.setList(contents); // Content listesi composition'a eklenir.
		request.setComposition(composition);  // composition da input'a eklenir.
		
		
		logger.info("Request json: " + new Gson().toJson(request));
		message.send(request); // değşiklikleri yapılan input, takipçiye send fonksiyonuyla gönderilir.
	}

}
