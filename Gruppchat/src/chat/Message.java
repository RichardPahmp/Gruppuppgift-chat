package chat;

import java.io.Serializable;
import java.util.Calendar;

public class Message implements Serializable{
	protected User sender;
	protected String dateSent;
	protected String dateReceived;

	public Message(User sender) {
		this.sender = sender;
	}

	public void setDateSent() {
		this.dateSent = setDate();
	}

	public void setDateReceived() {
		this.dateReceived = setDate();
	}

	private String setDate() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) + " " + (cal.get(Calendar.MONTH) + 1) + " " + cal.get(Calendar.DATE) + " "
				+ cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	}

	public String getDateSent() {
		return dateSent;
	}

	public String getDateReceived() {
		return dateReceived;
	}
}
