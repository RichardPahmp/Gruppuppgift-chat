package chat;

import javax.swing.ImageIcon;

public class ServerMessage extends Message {
	private String text;
	private ImageIcon icon;
	
	public ServerMessage(User sender, String text, ImageIcon icon) {
		super(sender);
		this.text = text;
		this.icon = icon;
	}
}
