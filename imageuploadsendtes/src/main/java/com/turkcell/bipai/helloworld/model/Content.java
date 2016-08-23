package com.turkcell.bipai.helloworld.model;


/**
 * 
 * TES servisine gönderilecek mesajın bilgilerini tutan model sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */

public class Content {

	/**
	 * Mesajın tip bilgisidir. 
	 * Tip bilgileri için http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/
	 * @see <a href="http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile/">http://www.bip.ai/documentations/coklu-kullaniciya-mesaj-gonderimi-liste-ile</a>
	 */
	private int		type;
	// Gönderilecek mesajın içeriği
	private String	message;
	// Gönderilecek dosyanın boyutu. max 20 MB büyüklüğünde olmalıdır. 
	private double size;
	// Gönderilecek dosyanın görüntülenecek thumbnail'inin köşe oranlarını ifade eder. Burada belirtilen değer 0.1 ile 3 aralığında olmalıdır. 
	private float ratio;
	
	public Content() {
		super();
	}

	public Content(int type, String message, double size, float ratio) {
		super();
		this.type = type;
		this.message = message;
		this.size = size;
		this.ratio = ratio;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

}
