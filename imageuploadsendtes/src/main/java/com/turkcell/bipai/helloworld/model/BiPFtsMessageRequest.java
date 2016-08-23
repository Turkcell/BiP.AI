package com.turkcell.bipai.helloworld.model;


/**
 * 
 * FTS servisine upload isteği göndermek için gereken bilgileri tutan talep (request) sınıfıdır. 
 * 
 * @author BiP AI
 * 
 */
public class BiPFtsMessageRequest {
	
	// Mesaj tekil id'si
	private String		txnid;
	// Mesaj alıcısı. Boş bırakılacak
	private String		receiver;
	// Avatar sahibi. Boş bırakılacak
	private	String 		avatarOwner;
	// Grup olup olmadığı. false değeri atancak.
	private	String 		isGroup;
	// Avatar olup olmadığı. false değeri atancak.
	private	String 		isAvatar;
	// Hedef kullanıcı. Boş bırakılacak.
	private	String 		toUser;
	// Dosya tipi. P -> Resim, V -> video
	private	String 		fileType;
	
	public String getTxnid() {
		return txnid;
	}
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getAvatarOwner() {
		return avatarOwner;
	}
	public void setAvatarOwner(String avatarOwner) {
		this.avatarOwner = avatarOwner;
	}
	public String getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}
	public String getIsAvatar() {
		return isAvatar;
	}
	public void setIsAvatar(String isAvatar) {
		this.isAvatar = isAvatar;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
