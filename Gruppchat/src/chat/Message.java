package chat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;

public class Message {
	private User sender;
	private Date date;
	
	public Message(User sender) {
		this.sender = sender;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
