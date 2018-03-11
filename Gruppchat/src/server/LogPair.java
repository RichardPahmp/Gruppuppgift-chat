package server;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class LogPair implements Serializable {
	private Date time;
	private String text;
	
	public LogPair(String text, Date time) {
		this.text = text;
		this.time = time;
	}
	
	public String getText() {
		return text;
	}
	
	public Date getTime() {
		return time;
	}
}