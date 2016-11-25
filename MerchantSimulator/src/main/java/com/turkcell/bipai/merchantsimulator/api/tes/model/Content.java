package com.turkcell.bipai.merchantsimulator.api.tes.model;



public class Content {

	private Integer				type;			// Mesaj türü, alabileceği değerler için Ctype sınıfına bakınız.
	
	private String				message;		// Metin mesajları için kullanıcının okuyacağı UTF-8 karakter dizisidir. 
												// Resim, ses ve video için ilgili dosya öncelikle FTS sunucusuna yüklenmelidir. 
												// Bu alana FTS’den alınan URL bilgisi eklenmelidir. Lokasyon ve RMM mesajlarında bu alan boş bırakılmalıdır.
	
	private Integer				size;			// FTS’e yüklenen dosyanın boyutu. Sadece resim, ses ve video mesaj türleri için bu alan zorunlu ve anlamlıdır.
	
	private Float				ratio;			// FTS’e yüklenen ön izleme (thumbnail) resminin boyutlarının oranı. Sadece resim, video mesaj türleri için kullanılır.
	
	private Float				lat;			// Sadece konum türü için bu alan zorunlu ve anlamlıdır. Gönderilecek konumun enlem değeridir.
	
	private Float				lon;			// Sadece lokasyon türü için bu alan zorunlu ve anlamlıdır. Gönderilecek konumun boylam değeridir.
	

	
	public Content() {
		super();
	}
	
	public Content(Integer type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getSize() {
		return size;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public Float getRatio() {
		return ratio;
	}
	
	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}
	
	public Float getLat() {
		return lat;
	}
	
	public void setLat(Float lat) {
		this.lat = lat;
	}
	
	public Float getLon() {
		return lon;
	}
	
	public void setLon(Float lon) {
		this.lon = lon;
	}

}
