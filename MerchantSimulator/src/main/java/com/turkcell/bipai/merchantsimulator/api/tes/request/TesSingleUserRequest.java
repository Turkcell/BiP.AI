package com.turkcell.bipai.merchantsimulator.api.tes.request;

import com.turkcell.bipai.merchantsimulator.api.tes.model.Receiver;

public class TesSingleUserRequest extends TesRequest{

	private Receiver receiver;

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
}
