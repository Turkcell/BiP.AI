package com.turkcell.bipai.merchantsimulator.api.payment.model;

public class Item {

	private Integer count;
	private Integer id;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Item:[id: "+id+" count: "+count+"]";
	}
}
