package com.turkcell.bipai.merchantsimulator.api.tes.model;
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