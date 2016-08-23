package com.turkcell.bipai.helloworld.model;

import com.google.gson.annotations.SerializedName;


/**
 * 
 * TES servisine gönderilecek mesajın bilgilerini tutan model sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */

public class Content {

	/*
	 * Mesajın tip bilgisidir. 
	 * Tip bilgileri için http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/
	 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile</a>
	 */
	private int		type;
	
	/*
	 *  Gönderilecek richmedia mesajının alt tipi. JSON çıktısında richmediatype 
	 *  olarak görünmesi gerektiği için SerializedName ile özelleştirme yapılmıştır. 
	 */
	@SerializedName("richmediatype")
	private int	richMediaType;
	
	/*
	 *  Gönderilecek richmediaların listesi JSON çıktısında richmedialist 
	 *  olarak görünmesi gerektiği için SerializedName ile özelleştirme yapılmıştır. 
	 */
	@SerializedName("richmedialist")
	private RichMedia[] richMediaList;
	
	public Content() {
		super();
	}

	public Content(int type, int richMediaType, RichMedia[] richMediaList) {
		super();
		this.type = type;
		this.richMediaType = richMediaType;
		this.richMediaList = richMediaList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRichMediaType() {
		return richMediaType;
	}

	public void setRichMediaType(int richMediaType) {
		this.richMediaType = richMediaType;
	}

	public RichMedia[] getRichMediaList() {
		return richMediaList;
	}

	public void setRichMediaList(RichMedia[] richMediaList) {
		this.richMediaList = richMediaList;
	}

}
