package com.turkcell.bipai.merchantsimulator.util;

public class AppConstants {
	public static final String 	USER 					= 	ConfigLoader.CONF_MAP.get("API_USER"); 			 				 // BIP Developer API kullanıcı adı.
	public static final String 	PASS 					= 	ConfigLoader.CONF_MAP.get("API_PASS");    		  			     // BIP Developer API şifresi.
	
	public static final String PAYMENT_BASE_URL			=	ConfigLoader.CONF_MAP.get("PAYMENT_BASE_URL");	  				 // Payment Servis url.
	public static final String PAYMENT_SERVICE 			=   PAYMENT_BASE_URL+ConfigLoader.CONF_MAP.get("PAYMENT_SERVICE");   // Payment Servis Charging Reservation endpoint.
	public static final String PAYMENT_COMMIT 			=   PAYMENT_BASE_URL+ConfigLoader.CONF_MAP.get("PAYMENT_COMMIT");    // Payment Servis Commitment endpoint.
	public static final Integer PAYMENT_CHANNEL 		=	Integer.parseInt(ConfigLoader.CONF_MAP.get("PAYMENT_CHANNEL"));  // Payment Servis Channel 
	public static final String 	TES_BASE_URI 			= 	ConfigLoader.CONF_MAP.get("TES_BASE_URI");						 // Payment Tes Servis url.
	public static final String 	TES_SINGLE_USER 		= 	TES_BASE_URI +ConfigLoader.CONF_MAP.get("TES_SINGLE_USER");		 // Payment tekil kullanıcıya mesaj atma endpoint. 
}
