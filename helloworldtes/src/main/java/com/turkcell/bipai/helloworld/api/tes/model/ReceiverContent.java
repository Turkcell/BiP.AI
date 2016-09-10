package com.turkcell.bipai.helloworld.model.tes.data;


/**
 * 
 * Çok kişiye farklı mesaj gönderilmek istendiğinde her mesaj ReceiverContent modeli olarak 
 * hazırlanır. Mesajlar Receive ve Composition nesneleri olarak oluşturulan ReceiverContent Listesine 
 * eklenir.
 * Format:
 * 
 <pre>
...
   "receivercontentlist":[ 
      { 
         "receiver":{ 
            ...
         },
         "composition":{ 
            ...
         },
         "expire":60
      },
      { 
         "receiver":{ 
            ...
         },
         "composition":{ 
            ...
         }
      }
   ]
...
 </pre>
 * @author BiP AI
 * @see <a href="http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/">Receiver Content List bilgileri</a>
 */

public class ReceiverContent {
	
	private Receiver	 	receiver;
	private Composition 	composition;
	private Integer			expire;
	
	public Receiver getReceiver() {
		return receiver;
	}
	
	
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	
	
	public Composition getComposition() {
		return composition;
	}
	
	
	public void setComposition(Composition composition) {
		this.composition = composition;
	}


	public Integer getExpire() {
		return expire;
	}


	public void setExpire(Integer expire) {
		this.expire = expire;
	}

}
