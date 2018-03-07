package client;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import chat.Message;
import chat.TextMessage;
import chat.User;

public class ClientController {
	private User user;
	private ClientViewer viewer;
	private Message message;
	private TextMessage textMessage;
	private ImageIcon image;
	private ArrayList<User> receivers;
	private UserList userList;
	private String text;

	public ClientController(ClientViewer viewer) {
		this.viewer = viewer;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void newMessage() {
		userList = viewer.getuserList();
		receivers = userList.getReceivers();
		text = viewer.getText();
		image = viewer.getImage();

		textMessage = new TextMessage(user, receivers, text, image);

	}

	public void receivedMessage(TextMessage message) {
		viewer.addMessageToList(message);

	}

}
