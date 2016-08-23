package com.turkcell.bipai.sendmessage.job;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.client.RestTemplate;

import com.turkcell.bipai.sendmessage.model.Content;
import com.turkcell.bipai.sendmessage.model.Receiver;
import com.turkcell.bipai.sendmessage.model.TesMultiMessageRequest;
import com.turkcell.bipai.sendmessage.model.TesMultiMessageResponse;
import com.turkcell.bipai.sendmessage.util.BasicAuthRestTemplate;

public class MessageJob {
	
	public void sendMessage(String message) {
		
		String			username		=	"username";
		String			password		=	"password";
		RestTemplate	restTemplate	=	new BasicAuthRestTemplate(username, password);
		
		TesMultiMessageRequest	request		=	new TesMultiMessageRequest();
		
		Content content = new Content();
		content.setMessage(message);
		content.setType(0);
	
		List<Receiver> receivers = new ArrayList<>();
		Receiver receiver = new Receiver();
		receiver.setAddress("90532xxxxxxx");
		receiver.setType(2);
		receivers.add(receiver);
		
		
		request.setContent(content);
		request.setTxnid(UUID.randomUUID().toString());
		request.setReceivers(receivers);
		
		TesMultiMessageResponse	response	=	restTemplate.postForObject("https://prptims.turkcell.com.tr/tes/rest/spi/sendmsgservlist", request, TesMultiMessageResponse.class);
		
		if (response.getResultcode() == 0) {
			System.out.println("SUCCESS");
		}
		
	}
	
	
}
