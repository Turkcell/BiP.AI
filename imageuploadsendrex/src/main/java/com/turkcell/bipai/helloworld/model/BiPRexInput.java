package com.turkcell.bipai.helloworld.model;


/**
 * Bu uygulama ile açılan REST servisin hello metoduna gönderilecek JSON parametrelerini tutan sınıftır. 
 * Parametre bilgileri için http://www.bip.ai/documentations/rex-api/
 * @author BiP AI
 * 
 * @see <a href="http://www.bip.ai/documentations/rex-api/">http://www.bip.ai/documentations/rex-api</a>
 */
public class BiPRexInput {

	private String		keyword;
	private String		msgId;
	private String		transactionId;
	private Integer		serviceId;
	private String		receiveDate;
	private String		sender;
	private Composition	composition;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Composition getComposition() {
		return composition;
	}

	public void setComposition(Composition composition) {
		this.composition = composition;
	}

}
