package it.unibo.tw.web.beans;

public class Messaggio {
	private String testo;
	private String sender;
	private String chatId;		//come si può vedere anche da chat.jsp, l'id della chat è l'id dell'evento
	
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public Messaggio(String testo, String sender, String chatId) {
		super();
		this.testo = testo;
		this.sender = sender;
		this.chatId = chatId;
	}
}
