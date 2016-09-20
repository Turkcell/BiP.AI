package com.turkcell.bipai.helloworld.api.tes.response;

import com.turkcell.bipai.helloworld.api.tes.model.Report;

/**
 * 
 * TES servisi üzerinden çok takipçiye farklı mesaj gönderildikten sonra TES servisinin döndüğü cevabı tutan sınıftır. 
 * 
 * @author BiP AI
 * 
 */
public class TesMultiDifferentMessageResponse extends TesResponse {

	private Report report; // Raporlama sistemi gelişme aşamasında olduğundan bu kısım şu anda TES tarafından null dönmektedir.

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
}
