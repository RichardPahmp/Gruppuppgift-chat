package client;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import chat.Message;
import chat.TextMessage;
import chat.User;

public class ClientController {
	private User user;
	private ClientViewer viewer;
	private Message message;
	private Icon icon;

	public ClientController(ClientViewer viewer) {
		this.viewer = viewer;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void newMessage() {
	}

	public void uploadImage() {
		// TODO Auto-generated method stub
	}


	public void setUploadedImage() {
		icon = viewer.getUploadedImage();

	}
}
