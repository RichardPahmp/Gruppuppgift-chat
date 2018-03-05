package chat;

import java.util.ArrayList;

import javax.swing.Icon;

public class ClientMessage extends Message {
	private ArrayList<User> receivers;
	private String messageText;
	private Icon image;
	
	public ClientMessage(User sender, ArrayList<User> receivers, String messageText, Icon image) {
		super(sender);
		this.receivers = receivers;
		this.messageText = messageText;
		this.image = image;
	}
}
