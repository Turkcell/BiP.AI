package com.turkcell.bipai.merchantsimulator.api.tes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RichMedia {

	
	private String 			title;			// Zengin Medya başlığı
	private String 			image;			// Zengin Medyaya ait fotoğraf. Öncelikle FTS API ile server'a yüklenmelidir. FTS API'den alınan URL buraya verilmeli.
	private Float  			ratio;			// Fotoğrafa ait ratio
	private String 			description;	// Zengin Medyanın 
	private String 			url;			// Zengin Medyadan yönlendirilecek URL
	private String 			urltext;		// URL'i temsil eden metin
	private String 			pollid;			// Ankete ait tekil id. Zengin Medya anket tipinde değilse bu değer boş olmalıdır.
	private List<Option> 	options;		// Ankete ait seçeneklerin tutulduğu model. Zengin Medya anket tipinde değilse bu değer boş olmalıdır.
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss.SSS ZZZZ") 
	private String 			pollendtime;	// Anket bitiş tarihi. Zengin Medya anket tipinde değilse bu değer boş olmalıdır.
	

	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getImage() {
		return image;
	}
	
	
	public void setImage(String image) {
		this.image = image;
	}
	
	
	public Float getRatio() {
		return ratio;
	}
	
	
	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getUrl() {
		return url;
	}
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getUrltext() {
		return urltext;
	}
	
	
	public void setUrltext(String urltext) {
		this.urltext = urltext;
	}
	
	public String getPollid() {
		return pollid;
	}
	
	
	public void setPollid(String pollid) {
		this.pollid = pollid;
	}
	
	
	public String getPollendtime() {
		return pollendtime;
	}
	
	public void setPollendtime(String pollendtime) {
		this.pollendtime = pollendtime;
	}
	

	public List<Option> getOptions() {
		return options;
	}
	
	
	public void setOptions(List<Option> options) {
		this.options = options;
	}
}
