
package com.turkcell.bipai.helloworld.model;

/**
 * 
 * Bu uygulama ile açılan REST servisin hello metoduna gönderilecek JSON parametrelerini tutan sınıftır. 
 * Parametre bilgileri için http://www.bip.ai/documentations/mesaj-alma/
 *
 * @author BiP AI
 * 
 * @see <a href="http://www.bip.ai/documentations/mesaj-alma/">http://www.bip.ai/documentations/mesaj-alma</a>
 */
public class BiPTesInput {

	private String	sender;
	private Integer	msgid;
	private String	sendtime;
	private String	type;
	private String	ctype;
	private String	content;


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

}
