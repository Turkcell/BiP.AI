package com.turkcell.bipai.sendmessage.model;

import java.util.List;

public class TesMultiMessageRequest {

	private String			txnid;
	private List<Receiver>	receivers;
	private Content			content;


	public String getTxnid() {
		return txnid;
	}


	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	public List<Receiver> getReceivers() {
		return receivers;
	}


	public void setReceivers(List<Receiver> receivers) {
		this.receivers = receivers;
	}


	public Content getContent() {
		return content;
	}


	public void setContent(Content content) {
		this.content = content;
	}

}
