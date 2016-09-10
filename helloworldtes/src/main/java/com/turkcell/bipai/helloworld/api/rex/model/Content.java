
package com.turkcell.bipai.helloworld.model.rex.data;

import java.util.Map;

/**
 * 
 * REX servisinin cevabında kullanılacak mesaj bilgilerini tutan sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class Content {

	private Integer				type;
	private String				message;
	private Map<String, Object>	data;

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

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
