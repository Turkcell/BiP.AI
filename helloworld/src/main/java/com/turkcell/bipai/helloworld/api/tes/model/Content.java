package com.turkcell.bipai.helloworld.api.tes.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * TES servisine gönderilecek içeriğin bilgilerini tutan model sınıfıdır. Her bir Content sınıfı composition list'teki 
 * bir elemanı oluşturur.
 *  Formatı:
<pre>
...
    "composition":{ 
        "list":[ 
           { 
   			"type":2,
    		"address":"9053XXXXXXXX"
           },
           {
		     "message":"https://prptims.turkcell.com.tr/scontent/p2p/03022016/08/Pff7af11422edb797be7015534d596d2c8846efe555cdcba06b11b09f81b9435c1.jpg",
             "ratio":0.5625,
             "type":2,
             "size":49628
           }
        ]
    }
...
</pre>

 * @see <a href="http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/"></a>
 * @author BiP AI
 * 
 */

public class Content {

	private Integer				type;			// Mesaj türü, alabileceği değerler için Ctype sınıfına bakınız.
	
	private String				message;		// Metin mesajları için kullanıcının okuyacağı UTF-8 karakter dizisidir. 
												// Resim, ses ve video için ilgili dosya öncelikle FTS sunucusuna yüklenmelidir. 
												// Bu alana FTS’den alınan URL bilgisi eklenmelidir. Lokasyon ve RMM mesajlarında bu alan boş bırakılmalıdır.
	
	private Integer				size;			// FTS’e yüklenen dosyanın boyutu. Sadece resim, ses ve video mesaj türleri için bu alan zorunlu ve anlamlıdır.
	
	private Float				ratio;			// FTS’e yüklenen ön izleme (thumbnail) resminin boyutlarının oranı. Sadece resim, video mesaj türleri için kullanılır.
	
	private Float				lat;			// Sadece konum türü için bu alan zorunlu ve anlamlıdır. Gönderilecek konumun enlem değeridir.
	
	private Float				lon;			// Sadece lokasyon türü için bu alan zorunlu ve anlamlıdır. Gönderilecek konumun boylam değeridir.
	
	/*
	 *  Gönderilecek richmedia mesajının tipi. JSON çıktısında richmediatype 
	 *  olarak görünmesi gerektiği için SerializedName ile özelleştirme yapılmıştır. 
	 *  Sadece RMM türü için bu alan zorunlu ve anlamlıdır. Gönderilecek RMM’in türü. Alabileceği değerler:
		0 : Tekil
		1 : Çoğul
		2 : Anket
	 */
	@SerializedName("richmediatype")
	private Integer				richMediaType;
	
	/*
	 *  Gönderilecek richmediaların listesi JSON çıktısında richmedialist 
	 *  olarak görünmesi gerektiği için SerializedName ile özelleştirme yapılmıştır. 
	 *  Gönderilecek RMM (Zengin İçerikli Medya)'in bilgilerinin tutulduğu modeldir. Sadece RMM türü için zorunlu ve anlamlıdır.
	 */
	@SerializedName("richmedialist")
	private List<RichMedia> 	richMediaList;
	
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

	public int getRichMediaType() {
		return richMediaType;
	}

	public void setRichMediaType(int richMediaType) {
		this.richMediaType = richMediaType;
	}

	public List<RichMedia> getRichMediaList() {
		return richMediaList;
	}

	public void setRichMediaList(List<RichMedia> richMediaList) {
		this.richMediaList = richMediaList;
	}

}
