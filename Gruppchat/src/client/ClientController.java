package client;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import chat.Message;
import chat.TextMessage;
import chat.User;

public class ClientController {
	private User user = new User("Anonym", new ImageIcon("images/Granny.png"));
	private ClientViewer viewer;
	private Message message;
	private TextMessage textMessage;
	private ImageIcon image;
	private ArrayList<User> receivers;
	private ArrayList<User> active;
	private String text;

	public ClientController(ClientViewer viewer) {
		newUser();
		this.viewer = viewer;
	}
	
	public void newUser() {
		UserInput userInput = new UserInput(this);	
		userInput.setVisible(true);
	}

	public void setUser(String name, ImageIcon image) {
		user = new User(name, image);
	}

	public void newMessage() {
		receivers = viewer.getSelectedActiveUsers();
		System.out.println(receivers);
		text = viewer.getText();
		image = viewer.getImage();
		textMessage = new TextMessage(user, receivers, text, image);
		viewer.eraseImage();
		receivedMessage(textMessage);
	}

	public void receivedMessage(TextMessage message) {
		viewer.addMessageToList(message);

	}

}