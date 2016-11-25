package com.turkcell.bipai.merchantsimulator.util;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TransactionHolder {
	private static final ConcurrentMap<String, String> TRANSACTION_MAP = new ConcurrentHashMap<String, String>();//Transaction - MSISDN verilerini tutan cache
	
	public static void put(String transactionID,String msisdn){
		TRANSACTION_MAP.put(transactionID, msisdn);
	}
	
	public static boolean doesTransactionIDexist(String transactionID){
		if(TRANSACTION_MAP.containsKey(transactionID)){
			return true;
		}else{
			return false;
		}
	}
	public static String getMsisdn(String transactionID){
		if(doesTransactionIDexist(transactionID)){
			return TRANSACTION_MAP.get(transactionID);
		}else{
			return null;
		}
	}
	
	public static void remove(String transactionID){
		if(doesTransactionIDexist(transactionID)){
			TRANSACTION_MAP.remove(transactionID);
		}
	}
}

