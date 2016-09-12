package com.turkcell.bipai.helloworld.api.tes.model;


/**
 * 
 * Çok kişiye farklı mesaj gönderilmek istendiğinde her mesaj ReceiverContent modeli olarak 
 * hazırlanır. Mesajlar Receiver ve Composition nesneleri olarak oluşturulan ReceiverContent Listesine 
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
	
	private Receiver	 	receiver;		// Mesajı alacak takipçi bilgilerinin tutulduğu model
	private Composition 	composition;	// Takipçiye gönderilecek mesaj bilgilerinin tutulduğu model
	private Integer			expire;			// Mesajın belirli sürü gösterilmesi ve silinmesi isteniyorsa, bu süreyi tutan alan
	
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
