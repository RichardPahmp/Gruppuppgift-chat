package client;

import javax.swing.ImageIcon;

import chat.Message;
import chat.TextMessage;
import chat.User;

public class ClientController {
	private User user;
	private ClientViewer viewer;
	private Message message;

	public ClientController() {
		viewer = new ClientViewer(this);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void newMessage() {
		message = new TextMessage(user, viewer.getRecievers(), viewer.getText(), null);
		System.out.println(message);
	}

	public void uploadImage() {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		ClientController test = new ClientController();
		ImageIcon icon = new ImageIcon("images/SmallMadeline.png");
		User user = new User("Test", icon);
		test.setUser(user);
	}
}
