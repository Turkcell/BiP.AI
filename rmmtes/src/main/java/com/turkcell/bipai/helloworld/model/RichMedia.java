package com.turkcell.bipai.helloworld.model;

/**
 * 
 * TES servisine gönderilecek RMM nesnesinin bilgilerini tutan model sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class RichMedia {
	private String title;
	private String image;
	private float ratio;
	private String description;
	private String url;
	private String urltext;
	
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
	public float getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
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
}
