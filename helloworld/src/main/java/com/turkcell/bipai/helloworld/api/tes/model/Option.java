package com.turkcell.bipai.helloworld.api.tes.model;


/**
 * 
 * Zengin İçerikli Medya (RMM) Anket olarak gönderildiğinde (richmediatype = 2) girilen seçenekleri
 * tutan model sınıfıdır.
 * Formatı:
<pre>
...
  "richmediatype":2,
  "richmedialist":[  
     {  
        ...
        "options":[  
           {  
              "optionid":1,
              "text":"İlk Seçenek"
           },
           {  
              "optionid":2,
              "text":"İkinci Seçenek"
           },
           {  
              "optionid":3,
              "text":"Üçüncü Seçenek"
           }
        ]
        ...
     }
  ]
...
</pre>
 * @see <a href="http://www.bip.ai/documentations/content-type-ozelindeki-kontroller/">Options bilgileri</a>
 * @author BiP AI
 * 
*/
public class Option {
	
	private Integer optionid;		// Seçeneğin tekil id'si
	private String text;			// Seçeneğe ait metin
	
	public Option(Integer optionid, String text) {
		super();
		this.optionid = optionid;
		this.text = text;
	}

	public Integer getOptionid() {
		return optionid;
	}
	
	public void setOptionid(Integer optionid) {
		this.optionid = optionid;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Options [optionid=" + optionid + ", text=" + text + "]";
	}
	
}
