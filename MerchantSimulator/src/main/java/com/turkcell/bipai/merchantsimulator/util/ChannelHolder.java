package com.turkcell.bipai.merchantsimulator.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelHolder {
	public static final ConcurrentMap<String, Integer> CHANNEL_MAP = new ConcurrentHashMap<String, Integer>();
	
	public static void setChannelID(String msisdn,int channelID){
		if (channelID==AppConstants.PAYMENT_CHANNEL) {
			if(CHANNEL_MAP.containsKey(msisdn)){
				CHANNEL_MAP.remove(msisdn);
			}
		}else{
			if (CHANNEL_MAP.containsKey(msisdn)) {
				CHANNEL_MAP.replace(msisdn, channelID);
			}else{
				CHANNEL_MAP.put(msisdn, channelID);
			}
		}
	
	}
	
	public static int getChannelID(String msisdn){
		if (CHANNEL_MAP.containsKey(msisdn)) {
			return CHANNEL_MAP.get(msisdn);
		} else {
			return AppConstants.PAYMENT_CHANNEL;
		}
	}
}
