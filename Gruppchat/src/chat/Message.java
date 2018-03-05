package chat;

import java.util.Calendar;

public class Message {
	private User sender;
	private String dateCreated;
	private String dateReceived;

	public Message(User sender) {
		this.sender = sender;
	}

	public void setDateCreated() {
		this.dateCreated = setDate();
	}

	public void setDateReceived() {
		this.dateReceived = setDate();
	}

	private String setDate() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) + " " + (cal.get(Calendar.MONTH) + 1) + " " + cal.get(Calendar.DATE) + " "
				+ cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public String getDateReceived() {
		return dateReceived;
	}
}
