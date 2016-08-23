package com.turkcell.bipai.sendmessage.model;

public class TesMultiMessageResponse {
	
	private String	txnid;
	private Integer	resultcode;
	private Report	report;


	public String getTxnid() {
		return txnid;
	}

	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}


	public Integer getResultcode() {
		return resultcode;
	}

	public void setResultcode(Integer resultcode) {
		this.resultcode = resultcode;
	}


	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
}
