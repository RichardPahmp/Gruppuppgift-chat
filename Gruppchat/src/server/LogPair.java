package server;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Represents a Date object paired with a string.
 * Used to save messages with a timestamp in the serverlog.
 * @author Richard
 *
 */
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