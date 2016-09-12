package com.turkcell.bipai.helloworld.api.tes.model;

/**
 * Takipçi mesaj gönderdiğinde veya abone olup/abonelikten çıktığında web servisinize iletilen mesajın JSON formatını tutan sınıftır. 
 * Gelen mesajlar methodlarınız tarafından bu sınıf kullanılarak okunur.
 * Parametre bilgileri için http://www.bip.ai/documentations/mesaj-alma/ ve http://www.bip.ai/documentations/bildirim-alma/
 *
 * @author BiP AI
 * 
 * @see <a href="http://www.bip.ai/documentations/mesaj-alma/">http://www.bip.ai/documentations/mesaj-alma</a>
 * @see <a href="http://www.bip.ai/documentations/bildirim-alma/">http://www.bip.ai/documentations/bildirim-alma/</a>
 */
public class TesInput {

	// -------------- mesaj ve bildirim alma için gelen ortak JSON kısımları -------------- 
	
	private String	sender;		// Mesajı gönderen kullanıcı bilgisi. Servisiniz MSISDN numaralarını kullanmaya yetkili tanımlandıysa
								// TES tarafından web servisinize iletilen mesajlar MSISDN tipindedir. Yetkili değilse Opak-Karıştırılmış
								// numara TES tarafından iletilir.
	
	private Integer	msgid;		// Mesaja ait eşsiz ID
	private String	sendtime;	// Mesajın gönderilme zamanı
	private String	type;		// TES'in gönderidiği iletinin mesaj mı bildirim mi olduğununu bilgisi. "M" ise mesaj "E" ise  bildirim gönderilmiştir.
	private String	ctype;		// İçeriğin türü. Alabileceği değerler için Ctype sınıfına bakınız.
	
	// --------------  ortak alan kısımların sonu -------------- 
	
	
	// type M ise gelen mesaj web servisinize iletilmiştir. Bu durumda content, pollid ve optionid değerleri gelen JSON içerisindedir.
	
	private String	content;	// Mesajın içeriğinin bilgisi. Metin ve lokasyon haricindeki diğer türler için URL bilgisi içermektedir.
	private Integer pollid;		// cType R iken doludur. Kullanıcının cevapladığı anketin idsi bildirilir.
	private Integer optionid;	// cType R iken doludur. Kullanıcının ankette hangi şıkkı cevapladığını içerir.
	
	
	// type E ise gelen bildirim web servisinize iletilmiştir. Bu durumda event değeri gelen JSON içerisindedir.
	
	private String 	event;		// Bildirim türüdür. Abonelikten çıkış bildirimi için "U", abonelik bildirimi için "S" değerini alır.


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public Integer getMsgid() {
		return msgid;
	}


	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}


	public String getSendtime() {
		return sendtime;
	}


	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCtype() {
		return ctype;
	}


	public void setCtype(String ctype) {
		this.ctype = ctype;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getPollid() {
		return pollid;
	}


	public void setPollid(Integer pollid) {
		this.pollid = pollid;
	}


	public Integer getOptionid() {
		return optionid;
	}


	public void setOptionid(Integer optionid) {
		this.optionid = optionid;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}
}