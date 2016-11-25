package com.turkcell.bipai.merchantsimulator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


import com.turkcell.bipai.merchantsimulator.api.payment.model.Item;

public class Products {
	private static Map<String, List<String>> productMap = ConfigLoader.PRODUCTS_MAP; // Config dosyasından gelen id=>[keyword array]
	private static final Logger logger = Logger.getLogger(Products.class.getName());

	/**
	 * @param productKeyword aranılan ürünün anahtar kelimesi
	 * @return productID anahtar kelimesinin ait ürünün idsini döner.Eğer bulunamazsa -1 döner.
	 */
	public static Integer getProduct(String productKeyword) {
		for (String productID : productMap.keySet()) {
			List<String> keywordSet = productMap.get(productID);
			for (String keyword : keywordSet) {
				if (productKeyword.toLowerCase().contains(keyword.toLowerCase())) {
					logger.info("urun bulundu id: " + productID);
					return Integer.parseInt(productID);
				}
			}
		}

		return -1;

	}

	/**
	 * Kullanıcının mesajı ayrıştırılarak list tipinde item modeli döner
	 * Ayrıştırma süreci öncelikle virgül ile başlar. Daha sonra boşluklara göre bölünür.
	 * Numara varsa ürün sayısı kabul edilir.
	 * @param message ayrıştırılacak mesajın tamamı
	 * @return mesajın ayrıştırılması sonucu oluşan item model listesi
	 */
	public static List<Item> orderParser(String message) {
		logger.info("Kullanıcının mesajı ayıklanıyor. Mesaj: '" + message + "'");
		message = message.toLowerCase();
		List<Item> list = new ArrayList<Item>();

		List<String> commaSeperated = Arrays.asList(message.split(","));
		for (String single : commaSeperated) {
			List<String> spaceSeperatedStrings = Arrays.asList(single.trim().split(" "));

			int count = 1;
			for (String spaceSeperated : spaceSeperatedStrings) {
				if (isInteger(spaceSeperated)) {
					int parsedCount = Integer.parseInt(spaceSeperated);
					if (parsedCount > 0) {
						count = parsedCount;
					}
				}
			}

			int productID = getProduct(single);
			if (productID > 0 && count > 0) {
				Item item = new Item();
				item.setCount(count);
				item.setId(productID);
				list.add(item);
			}

		}
		logger.info("Ayıklanma tamamlandı.Totalde bulunan farklı urun sayısı: " + list.size());
		return list;
	}
	
	

	/**
	 * @param s string değerinin integer olduğunu kontrol eder
	 * @return 
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

}
