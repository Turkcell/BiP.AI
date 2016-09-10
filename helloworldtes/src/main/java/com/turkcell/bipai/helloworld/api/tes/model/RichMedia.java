package com.turkcell.bipai.helloworld.api.tes.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * Zengin İçerikli Medya (RMM) bilgilerinin tutulduğu modeldir. Tekil ve Anket tipi RMM gönderimlerinde listeye 1 adet obje eklenir. 
 * Çoğul tipi gönderimlerde listedeki ilk eleman RMM’in üst kısmında, diğer elemanlar alt madde olarak görülür.
 * Formatı:
 <pre>
...
"richmedialist":[  
	 {  
	    "title":"Survivor",
	    "image":"https://testtims.turkcell.com.tr/scontent/p2p/28032016/12/survivor.jpeg",
	    "ratio":0.5,
	    "description":"Survivor'da kim kalsın?",
	    "pollid":"Survivor-1",
	    "pollendtime":"04.06.2016 10:55:00.000 +0300",
	    "options":[  
	       {  
	          "optionid":1,
	          "text":"YILMAZ MORGUL"
	       },
	       {  
	          "optionid":2,
	          "text":"YUNUS GUNCE"
	       },
	       {  
	          "optionid":3,
	          "text":"YATTAR"
	       }
	    ]
	 }
]
...
</pre>
 * @see <a href="http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/">RMM bilgileri</a>
 * @author BiP AI
 * 
*/

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
