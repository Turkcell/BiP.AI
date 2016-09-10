package com.turkcell.bipai.helloworld.model.tes.data;

import java.util.List;

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

	private Integer				type;
	private String				message;
	private Integer				size;
	private Float				ratio;
	private Float				lat;
	private Float				lon;
	private Integer				richmediatype;
	private List<RichMedia>		richmedialist;

	
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
	
	
	public Integer getRichmediatype() {
		return richmediatype;
	}
	
	
	public void setRichmediatype(Integer richmediatype) {
		this.richmediatype = richmediatype;
	}
	
	
	public List<RichMedia> getRichmedialist() {
		return richmedialist;
	}
	
	
	public void setRichmedialist(List<RichMedia> richmedialist) {
		this.richmedialist = richmedialist;
	}

}
