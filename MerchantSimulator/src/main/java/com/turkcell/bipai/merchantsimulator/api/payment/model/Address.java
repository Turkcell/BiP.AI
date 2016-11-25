package com.turkcell.bipai.merchantsimulator.api.payment.model;

public class Address {
	private String addressId;
	private String alias;
	private String name;
	private String surname;
	private String country;
	private String city;
	private String address;
	private String quarter;
	private String district;
	private boolean isDefault;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", alias=" + alias + ", name=" + name + ", surname=" + surname
				+ ", country=" + country + ", city=" + city + ", address=" + address + ", quarter=" + quarter
				+ ", district=" + district + ", isDefault=" + isDefault + "]";
	}

}
