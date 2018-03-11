package chat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable{
	protected User sender;
	
	protected LocalDateTime timeSent, timeReceived;

	public Message(User sender) {
		this.sender = sender;
	}
	
	public void setTimeSent() {
		timeSent = LocalDateTime.now();
	}
	
	public void setTimeReceived() {
		timeReceived = LocalDateTime.now();
	}
	
	public LocalDateTime getTimeSent() {
		return timeSent;
	}
	
	public LocalDateTime getTimeReceived() {
		return timeReceived;
	}
	
}
