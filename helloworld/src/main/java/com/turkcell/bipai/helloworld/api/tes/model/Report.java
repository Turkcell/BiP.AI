package com.turkcell.bipai.helloworld.api.tes.model;


/**
 * 
 * Geliştirilme aşamasındadır, TES API tarafından şu anda null değeri dönmektetidir.
 * TES tarafından, web servisinizin yaptığı isteğe cevap olarak gönderilen rapor.
 * Formatı:
 <pre>
...
{
  ...
  "report": {
	    "fileCount": 0,
	    "accepted": 2,
	    "invalid": 0,
	    "actual": 2
	}
  ...
}
...
</pre>
 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">Gönderim raporu</a>
 * @author BiP AI
 * 
*/

public class Report {

	private Integer fileCount;	// Dosya sıyısı
	private Integer accepted;	// Kabul edilen kullanıcı sayısı
	private Integer invalid;	// Hatalı/geri çevrilen alıcı sayısı
	private Integer actual;		// Gönderimi yapılan alıcı sayısı
	
	public Integer getFileCount() {
		return fileCount;
	}
	
	
	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}
	
	
	public Integer getAccepted() {
		return accepted;
	}
	
	
	public void setAccepted(Integer accepted) {
		this.accepted = accepted;
	}
	
	
	public Integer getInvalid() {
		return invalid;
	}
	
	
	public void setInvalid(Integer invalid) {
		this.invalid = invalid;
	}
	
	
	public Integer getActual() {
		return actual;
	}
	
	
	public void setActual(Integer actual) {
		this.actual = actual;
	}


	@Override
	public String toString() {
		return "Report [fileCount=" + fileCount + ", accepted=" + accepted
				+ ", invalid=" + invalid + ", actual=" + actual + "]";
	}
	
}
