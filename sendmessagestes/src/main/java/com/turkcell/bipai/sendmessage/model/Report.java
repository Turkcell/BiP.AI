package com.turkcell.bipai.sendmessage.model;

public class Report {
	
	private Integer	fileCount;
	private Integer	accepted;
	private Integer	invalid;
	private Integer	actual;


	public Integer getFileCount() {
		return fileCount;
	}

	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}


	public Integer getAccepted() {
		return accepted;
	}

	public void setAccepted(Integer accepted) {
		this.accepted = accepted;
	}


	public Integer getInvalid() {
		return invalid;
	}

	public void setInvalid(Integer invalid) {
		this.invalid = invalid;
	}


	public Integer getActual() {
		return actual;
	}

	public void setActual(Integer actual) {
		this.actual = actual;
	}

	@Override
	public String toString() {
		return "Report [accepted=" + accepted + ", invalid=" + invalid + ", actual=" + actual + "]";
	}
	
}
