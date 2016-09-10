package com.turkcell.bipai.helloworld.model.tes.response;

import com.turkcell.bipai.helloworld.model.tes.data.Report;

/**
 * 
 * TES servisi üzerinden gelen mesajın bilgilerini tutan cevap (response) sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class TesMultiUserListRepsonse extends TesResponse {

	private Report report;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
}
