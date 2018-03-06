package chat;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class TextMessage extends Message implements Serializable{
	private ArrayList<User> receivers;
	private String messageText;
	private ImageIcon image;
	
	public TextMessage(User sender, ArrayList<User> receivers, String messageText, ImageIcon image) {
		super(sender);
		this.receivers = receivers;
		this.messageText = messageText;
		this.image = image;
	}
	
	public void clearReceivers() {
		this.receivers.clear();
	}
	
	public ArrayList<User> getReceivers(){
		return receivers;
	}
	
	public String getText() {
		return this.messageText;
	}
	
	public Icon getImage() {
		return this.image;
	}
	
	public  User getSender() {
		return this.sender;
	}
}
